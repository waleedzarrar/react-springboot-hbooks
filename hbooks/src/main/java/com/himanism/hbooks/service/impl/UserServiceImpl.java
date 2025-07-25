package com.himanism.hbooks.service.impl;

import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;
import com.himanism.hbooks.entity.User;
import com.himanism.hbooks.mapper.UserMapper;
import com.himanism.hbooks.repository.UserRepository;
import com.himanism.hbooks.service.UserService;
import com.himanism.hbooks.service.helper.UserServiceHelper;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserServiceHelper userServiceHelper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserServiceHelper userServiceHelper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userServiceHelper = userServiceHelper;
    }

    @Override
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        log.info("Creating user with email: {}", userRequestDTO.getEmail());

        // Map DTO to entity (roles are mapped to persistent RoleEntity)
        User user = userMapper.toEntity(userRequestDTO);

        // Prepare password: encode or generate default if absent
        user = userServiceHelper.prepareUserForSave(user);

        // Save user to DB (roles must exist already)
        User savedUser = userRepository.save(user);

        // Map saved entity to response DTO
        UserResponseDTO responseDTO = userMapper.toDto(savedUser);

        // Generate and set full name (not from MapStruct)
        responseDTO.setFullName(userServiceHelper.generateFullName(savedUser));

        log.info("Created User with ID: {}", savedUser.getId());

        return responseDTO;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> dtos = userMapper.toDtoList(users);

        // Set fullName for each DTO as MapStruct ignores it
        for (int i = 0; i < dtos.size(); i++) {
            dtos.get(i).setFullName(userServiceHelper.generateFullName(users.get(i)));
        }

        return dtos;
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        UserResponseDTO dto = userMapper.toDto(user);
        dto.setFullName(userServiceHelper.generateFullName(user));
        return dto;
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Use mapper to update entity fields except id & password
        userMapper.updateEntityFromDto(userRequestDTO, existingUser);

        // If password was provided explicitly, encode it; otherwise keep existing password
        if (userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isBlank()) {
        	existingUser.setPassword(userServiceHelper.encodePassword(userRequestDTO.getPassword()));
        }

        User updatedUser = userRepository.save(existingUser);

        UserResponseDTO dto = userMapper.toDto(updatedUser);
        dto.setFullName(userServiceHelper.generateFullName(updatedUser));
        return dto;
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
        log.info("Deleted user with ID: {}", id);
    }
}