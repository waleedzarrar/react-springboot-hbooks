package com.himanism.hbooks.service;

import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;

import java.util.List;

public interface UserServiceInterface {
    UserResponseDTO createUser(UserRequestDTO dto);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Long id, UserRequestDTO dto);
    boolean deleteUserById(Long id);
}

