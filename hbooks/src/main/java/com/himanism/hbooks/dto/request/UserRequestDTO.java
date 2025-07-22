package com.himanism.hbooks.dto.request;

import com.himanism.hbooks.enums.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    @NotBlank(message = "Username is mandatory")
    private String username;

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
    
    @NotEmpty(message = "At least one role must be assigned")
    private Set<Role> roles;
}
