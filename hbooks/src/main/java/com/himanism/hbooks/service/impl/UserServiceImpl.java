package com.himanism.hbooks.service.impl;


import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;
import com.himanism.hbooks.entity.User;
import com.himanism.hbooks.repository.UserRepository;
import com.himanism.hbooks.service.UserService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        log.info("Creating user with email: {}", userRequestDTO.getEmail());
        User user = mapToEntity(userRequestDTO);
        User savedUser = userRepository.save(user);
        log.info("Created user with ID: {}", savedUser.getId());
        return mapToResponseDTO(savedUser);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        log.debug("Getting all users");
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        log.debug("Getting user by ID: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.warn("User not found with ID: {}", id);
            return new RuntimeException("User not found");
        });
        return mapToResponseDTO(user);
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        log.debug("Updating user with ID: {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> {
            log.warn("User not found with ID: {}", id);
            return new RuntimeException("User not found");
        });

        // Update fields
        user.setFirstName(userRequestDTO.getFirstName());
        user.setLastName(userRequestDTO.getLastName());
        user.setEmail(userRequestDTO.getEmail());
        // update other fields as needed

        User updatedUser = userRepository.save(user);
        log.info("Updated user with ID: {}", id);
        return mapToResponseDTO(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Deleting user with ID: {}", id);
        userRepository.deleteById(id);
        log.info("Deleted user with ID: {}", id);
    }


    // Helper methods to map DTOs <-> Entities
    private User mapToEntity(UserRequestDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        // map other fields
        return user;
    }

    private UserResponseDTO mapToResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        // map other fields
        return dto;
    }
}
