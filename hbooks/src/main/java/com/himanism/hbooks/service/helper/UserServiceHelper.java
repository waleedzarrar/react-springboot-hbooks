package com.himanism.hbooks.service.helper;

import com.himanism.hbooks.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceHelper {

    private final PasswordEncoder passwordEncoder;

    public String generateFullName(User user) {
        StringBuilder sb = new StringBuilder();
        if (user.getFirstName() != null && !user.getFirstName().isBlank()) {
            sb.append(user.getFirstName().trim()).append(" ");
        }
        if (user.getMiddleName() != null && !user.getMiddleName().isBlank()) {
            sb.append(user.getMiddleName().trim()).append(" ");
        }
        if (user.getLastName() != null && !user.getLastName().isBlank()) {
            sb.append(user.getLastName().trim());
        }
        String fullName = sb.toString().trim();
        log.debug("Generated full name: {}", fullName);
        return fullName;
    }

    public String generateEncodedPassword(User user) {
        String firstName = user.getFirstName() != null ? user.getFirstName().trim() : "";
        String firstPart = firstName.length() >= 4 ? firstName.substring(0, 4) : firstName;
        firstPart = firstPart.toUpperCase();

        LocalDate dob = user.getDateOfBirth();
        int age = 0;
        if (dob != null) {
            age = Period.between(dob, LocalDate.now()).getYears();
        }

        String rawPassword = firstPart + age;
        log.debug("Generating encoded password from raw password: {}", rawPassword);
        return passwordEncoder.encode(rawPassword);
    }

// Uncomment and use if you want to prepare user for save
//    public User prepareUserForSave(User user) {
//        if (user.getPassword() == null || user.getPassword().isBlank()) {
//            user.setPassword(generateEncodedPassword(user));
//            log.info("Generated password for new user");
//        } else {
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            log.info("Encoded provided raw password for user");
//        }
//        return user;
//    }
}
