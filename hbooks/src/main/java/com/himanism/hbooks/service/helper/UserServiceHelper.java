package com.himanism.hbooks.service.helper;

import com.himanism.hbooks.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserServiceHelper {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String buildFullName(String firstName, String middleName, String lastName) {
        StringBuilder fullName = new StringBuilder();
        if (firstName != null && !firstName.isBlank()) {
            fullName.append(firstName.trim());
        }
        if (middleName != null && !middleName.isBlank()) {
            fullName.append(" ").append(middleName.trim());
        }
        if (lastName != null && !lastName.isBlank()) {
            fullName.append(" ").append(lastName.trim());
        }
        return fullName.toString().trim();
    }

    public String generateEncodedPassword(String firstName, LocalDate dateOfBirth) {
        String firstNamePart = "";
        if (firstName != null && !firstName.isEmpty()) {
            firstNamePart = firstName.substring(0, Math.min(4, firstName.length())).toUpperCase();
        }
        String dobYearPart = (dateOfBirth != null) ? String.valueOf(dateOfBirth.getYear()) : "";
        String rawPassword = firstNamePart + dobYearPart;
        return passwordEncoder.encode(rawPassword);
    }

    public void prepareUserForSave(User user) {
        String fullName = buildFullName(user.getFirstName(), user.getMiddleName(), user.getLastName());
        user.setFullName(fullName);

        String encodedPwd = generateEncodedPassword(user.getFirstName(), user.getDateOfBirth());
        user.setPassword(encodedPwd);
    }
}
