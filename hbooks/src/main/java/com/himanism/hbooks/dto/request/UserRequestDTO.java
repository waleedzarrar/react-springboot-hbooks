package com.himanism.hbooks.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be minimum 6 characters")
    private String password;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    private String middleName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    private LocalDate dateOfBirth;

    @NotEmpty(message = "User must have at least one role")
    private Set<com.himanism.hbooks.enums.Role> roles;

    @NotBlank(message = "Mobile number is mandatory")
    private String mobileNo;

    @NotBlank(message = "Gender is mandatory")
    private String gender;
}