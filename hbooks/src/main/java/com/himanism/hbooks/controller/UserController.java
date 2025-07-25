package com.himanism.hbooks.controller;


import com.himanism.hbooks.dto.UserWithStudentProfileDTO;
import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;
import com.himanism.hbooks.entity.StudentProfile;
import com.himanism.hbooks.entity.User;
import com.himanism.hbooks.mapper.UserMapper;
import com.himanism.hbooks.repository.StudentProfileRepository;
import com.himanism.hbooks.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final StudentProfileRepository studentProfileRepository;
    private final UserMapper userMapper;
    

    public UserController(UserService userService, UserRepository userRepository, StudentProfileRepository studentProfileRepository, UserMapper userMapper) {
        this.userService = userService;
		this.userRepository = userRepository;
		this.studentProfileRepository = studentProfileRepository;
		this.userMapper = userMapper;
    }
    
    @GetMapping("/{id}/withStudentProfile")
    public ResponseEntity<UserWithStudentProfileDTO> getUserWithStudentProfile(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        StudentProfile studentProfile = studentProfileRepository.findById(id).orElse(null);

        UserWithStudentProfileDTO dto = userMapper.toUserWithStudentProfileDto(user, studentProfile);

        return ResponseEntity.ok(dto);
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