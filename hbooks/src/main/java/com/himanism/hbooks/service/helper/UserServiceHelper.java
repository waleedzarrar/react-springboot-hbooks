package com.himanism.hbooks.service.helper;

import com.himanism.hbooks.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;

/**
 * Helper class for User-related utility methods.
 */
@Component
@RequiredArgsConstructor
public class UserServiceHelper {

    private final PasswordEncoder passwordEncoder;

    /**
     * Generates full name from first, middle (optional), and last names.
     *
     * @param user the User entity
     * @return the full name concatenated with spaces
     */
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
        return sb.toString().trim();
    }

    /**
     * Generates a default encoded password.
     * Password format: first 4 letters of firstName (uppercase) + age in years.
     *
     * @param user the User entity
     * @return the encoded password string
     */
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
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Prepare the User entity before saving.
     * Encodes password if absent or raw password was provided.
     *
     * @param user the User entity to prepare
     * @return the prepared User entity with encoded password
     */
//    public User prepareUserForSave(User user) {
//        if (user.getPassword() == null || user.getPassword().isBlank()) {
//            user.setPassword(generateEncodedPassword(user));
//        } else {
//            // encode if password is provided (assuming raw password)
//            user.setPassword(passwordEncoder.encode(user.getPassword()));
//        }
//
//        // Optionally you could set a fullName field here if your User entity has it:
//        // user.setFullName(generateFullName(user));
//
//        return user;
//    }
}
