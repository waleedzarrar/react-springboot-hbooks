package com.himanism.hbooks.service.helper;

import com.himanism.hbooks.entity.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Set;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserServiceHelper {

    private final PasswordEncoder passwordEncoder;

    /**
     * Generate full name from User's firstName, middleName and lastName.
     * Trims spaces and returns concatenated string.
     *
     * @param user the User entity
     * @return fullName string
     */
    public String generateFullName(User user) {
        StringBuilder fullName = new StringBuilder();

        if (user.getFirstName() != null && !user.getFirstName().isBlank()) {
            fullName.append(user.getFirstName().trim()).append(" ");
        }
        if (user.getMiddleName() != null && !user.getMiddleName().isBlank()) {
            fullName.append(user.getMiddleName().trim()).append(" ");
        }
        if (user.getLastName() != null && !user.getLastName().isBlank()) {
            fullName.append(user.getLastName().trim());
        }

        return fullName.toString().trim();
    }

    /**
     * Parses comma separated roles string into a Set of trimmed role names.
     * (May not be used now as you use enums, but kept for backward compatibility.)
     *
     * @param rolesString the comma separated string of roles
     * @return Set of roles as strings
     */
    public Set<String> parseRolesString(String rolesString) {
        if (rolesString == null || rolesString.isBlank()) {
            return Collections.emptySet();
        }
        return Arrays.stream(rolesString.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());
    }

    /**
     * Generates default encoded password based on User's firstName and age.
     * Logic: First 4 letters of firstName uppercase + age (years from dateOfBirth).
     *
     * @param user the User entity
     * @return encoded password string
     */
    public String generateEncodedPassword(User user) {
        String firstName = (user.getFirstName() != null) ? user.getFirstName().trim() : "";
        String firstPart = firstName.length() >= 4 ? firstName.substring(0, 4) : firstName;
        firstPart = firstPart.toUpperCase();

        LocalDate dob = user.getDateOfBirth();
        int age = 0;
        if (dob != null) {
            age = Period.between(dob, LocalDate.now()).getYears();
        }

        String rawPassword = firstPart + age;

        log.info("Generating encoded password from raw password: {}", rawPassword);

        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Prepares User entity for save:
     * - If password is null or blank, generates default password and encodes it.
     * - If password is provided, encodes it.
     *
     * @param user the User entity
     * @return User entity with encoded password
     */
    public User prepareUserForSave(User user) {
        if (user.getPassword() == null || user.getPassword().isBlank()) {
            String encodedDefaultPassword = generateEncodedPassword(user);
            user.setPassword(encodedDefaultPassword);
            log.info("Generated default password for user: {}", user.getEmail());
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            log.info("Encoded provided password for user: {}", user.getEmail());
        }
        return user;
    }
    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}
