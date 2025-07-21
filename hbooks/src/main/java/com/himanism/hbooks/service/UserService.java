package com.himanism.hbooks.service;

import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO dto);
    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO updateUser(Long id, UserRequestDTO dto);
    boolean deleteUserById(Long id);
}

























//
//package com.himanism.hbooks.service;
//
//import com.himanism.hbooks.dto.request.UserRequestDTO;
//import com.himanism.hbooks.dto.response.UserResponseDTO;
//import com.himanism.hbooks.entity.User;
//import com.himanism.hbooks.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UserService {
//    @Autowired
//    private UserRepository userRepository;
//
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public UserResponseDTO createUser(UserRequestDTO dto) {
//        User user = new User();
//        user.setFirstName(dto.getFirstName());
//        user.setMiddleName(dto.getMiddleName());
//        user.setLastName(dto.getLastName());
//        user.setEmail(dto.getEmail());
//        user.setMobileNo(dto.getMobileNo());
//        user.setGender(dto.getGender());
//        user.setDateOfBirth(dto.getDateOfBirth());
//
//        // Set fullName
//        StringBuilder fullName = new StringBuilder(dto.getFirstName());
//        if (dto.getMiddleName() != null && !dto.getMiddleName().trim().isEmpty()) {
//            fullName.append(" ").append(dto.getMiddleName().trim());
//        }
//        fullName.append(" ").append(dto.getLastName());
//        user.setFullName(fullName.toString().trim());
//
//        // Password: FIRST 4 letters of firstName (ALL CAPS) + year of DOB
//        String firstNamePart = "";
//        if (dto.getFirstName() != null && !dto.getFirstName().isEmpty()) {
//            firstNamePart = dto.getFirstName()
//                .substring(0, Math.min(4, dto.getFirstName().length()))
//                .toUpperCase();
//        }
//        String dobYearPart = (dto.getDateOfBirth() != null)
//                ? String.valueOf(dto.getDateOfBirth().getYear()) : "";
//        String rawPassword = firstNamePart + dobYearPart;
//
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//        user.setPassword(encodedPassword);
//
//        User saved = userRepository.save(user);
//
//        return userToResponse(saved);
//    }
//
//    public UserResponseDTO getUserById(Long id) {
//        Optional<User> user = userRepository.findById(id);
//        return user.map(this::userToResponse).orElse(null);
//    }
//
//    public List<UserResponseDTO> getAllUsers() {
//        return userRepository.findAll()
//                .stream()
//                .map(this::userToResponse)
//                .collect(Collectors.toList());
//    }
//
//    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
//        Optional<User> userOpt = userRepository.findById(id);
//        if (userOpt.isEmpty()) {
//            return null;
//        }
//        User user = userOpt.get();
//        boolean recalculatePassword = false;
//
//        if (dto.getFirstName() != null && !dto.getFirstName().equals(user.getFirstName())) {
//            user.setFirstName(dto.getFirstName());
//            recalculatePassword = true;
//        }
//        if (dto.getMiddleName() != null) user.setMiddleName(dto.getMiddleName());
//        if (dto.getLastName() != null) user.setLastName(dto.getLastName());
//        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
//        if (dto.getMobileNo() != null) user.setMobileNo(dto.getMobileNo());
//        if (dto.getGender() != null) user.setGender(dto.getGender());
//        if (dto.getDateOfBirth() != null && !dto.getDateOfBirth().equals(user.getDateOfBirth())) {
//            user.setDateOfBirth(dto.getDateOfBirth());
//            recalculatePassword = true;
//        }
//        // Recalculate fullName
//        StringBuilder fullName = new StringBuilder(user.getFirstName());
//        if (user.getMiddleName() != null && !user.getMiddleName().trim().isEmpty()) {
//            fullName.append(" ").append(user.getMiddleName().trim());
//        }
//        fullName.append(" ").append(user.getLastName());
//        user.setFullName(fullName.toString().trim());
//
//        // If firstName or dateOfBirth changed, recalculate password
//        if (recalculatePassword) {
//            String firstNamePart = user.getFirstName() != null
//                ? user.getFirstName().substring(0, Math.min(4, user.getFirstName().length())).toUpperCase()
//                : "";
//            String dobYearPart = user.getDateOfBirth() != null
//                ? String.valueOf(user.getDateOfBirth().getYear()) : "";
//            String rawPassword = firstNamePart + dobYearPart;
//            String encodedPassword = passwordEncoder.encode(rawPassword);
//            user.setPassword(encodedPassword);
//        }
//
//        User saved = userRepository.save(user);
//        return userToResponse(saved);
//    }
//
//    public boolean deleteUserById(Long id) {
//        if (userRepository.existsById(id)) {
//            userRepository.deleteById(id);
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private UserResponseDTO userToResponse(User user) {
//        return new UserResponseDTO(
//                user.getId(),
//                user.getFirstName(),
//                user.getMiddleName(),
//                user.getLastName(),
//                user.getFullName(),
//                user.getEmail(),
//                user.getMobileNo(),
//                user.getGender(),
//                user.getDateOfBirth()
//        );
//    }
//}

