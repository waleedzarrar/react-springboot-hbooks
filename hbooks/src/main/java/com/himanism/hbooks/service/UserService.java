package com.himanism.hbooks.service;

import com.himanism.hbooks.dto.UserRequestDTO;
import com.himanism.hbooks.dto.UserResponseDTO;
import com.himanism.hbooks.entity.User;
import com.himanism.hbooks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setMobileNo(dto.getMobileNo());
        user.setGender(dto.getGender());
        user.setDateOfBirth(dto.getDateOfBirth());

        // Set fullName (handles blank middle name)
        StringBuilder fullName = new StringBuilder(dto.getFirstName());
        if (dto.getMiddleName() != null && !dto.getMiddleName().trim().isEmpty()) {
            fullName.append(" ").append(dto.getMiddleName().trim());
        }
        fullName.append(" ").append(dto.getLastName());
        user.setFullName(fullName.toString().trim());

        // Password: first 4 letters of first name (ALL CAPS) + year of DOB
        String firstNamePart = "";
        if (dto.getFirstName() != null && !dto.getFirstName().isEmpty()) {
            firstNamePart = dto.getFirstName()
                                .substring(0, Math.min(4, dto.getFirstName().length()))
                                .toUpperCase();
        }
        String dobYearPart = (dto.getDateOfBirth() != null) ?
                              String.valueOf(dto.getDateOfBirth().getYear()) : "";
        String rawPassword = firstNamePart + dobYearPart;

        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        User saved = userRepository.save(user);

        UserResponseDTO response = new UserResponseDTO();
        response.setId(saved.getId());
        response.setFirstName(saved.getFirstName());
        response.setMiddleName(saved.getMiddleName());
        response.setLastName(saved.getLastName());
        response.setFullName(saved.getFullName());
        response.setEmail(saved.getEmail());
        response.setMobileNo(saved.getMobileNo());
        response.setGender(saved.getGender());
        response.setDateOfBirth(saved.getDateOfBirth());
        return response;
    }

    public boolean deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
