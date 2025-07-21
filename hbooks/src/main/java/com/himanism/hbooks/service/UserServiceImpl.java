package com.himanism.hbooks.service;

import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;
import com.himanism.hbooks.entity.User;
import com.himanism.hbooks.exception.ResourceNotFoundException;
import com.himanism.hbooks.mapper.UserMapper;
import com.himanism.hbooks.repository.UserRepository;
import com.himanism.hbooks.service.helper.UserServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserServiceHelper userHelper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserServiceHelper userHelper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userHelper = userHelper;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        try {
            User user = userMapper.toEntity(dto);

            // Use helper to set fullName and password
            userHelper.prepareUserForSave(user);

            User savedUser = userRepository.save(user);
            return userMapper.toResponseDto(savedUser);
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while creating user: " + e.getMessage(), e);
        }
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
            return userMapper.toResponseDto(user);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch user: " + e.getMessage(), e);
        }
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        try {
            List<User> users = userRepository.findAll();
            return userMapper.toResponseDtoList(users);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all users: " + e.getMessage(), e);
        }
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

            // Update entity fields from DTO
            userMapper.updateUserFromDto(dto, user);

            // Recalculate fullName and password
            userHelper.prepareUserForSave(user);

            User saved = userRepository.save(user);
            return userMapper.toResponseDto(saved);
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean deleteUserById(Long id) {
        try {
            if (!userRepository.existsById(id)) {
                throw new ResourceNotFoundException("User not found with id: " + id);
            }
            userRepository.deleteById(id);
            return true;
        } catch (ResourceNotFoundException ex) {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete user: " + e.getMessage(), e);
        }
    }
}
