package com.himanism.hbooks.controller;


import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;
import com.himanism.hbooks.service.UserService;

import jakarta.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.info("Received create user request for email: {}", userRequestDTO.getEmail());
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        log.info("User created with id: {}", createdUser.getId());
        return ResponseEntity.status(201).body(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        log.debug("Received get user by id request for id: {}", id);
        UserResponseDTO user = userService.getUserById(id);
        log.info("Returned user details for id: {}", id);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        log.debug("Received get all users request");
        List<UserResponseDTO> users = userService.getAllUsers();
        log.info("Returned {} users", users.size());
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        log.debug("Received update user request for id: {}", id);
        UserResponseDTO updatedUser = userService.updateUser(id, userRequestDTO);
        log.info("Updated user with id: {}", id);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        log.info("Received delete user request for id: {}", id);
        userService.deleteUserById(id);
        log.info("Deleted user with id: {}", id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
