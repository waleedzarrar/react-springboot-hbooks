package com.himanism.hbooks.dto.response;

import com.himanism.hbooks.enums.Role;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName; // set by helper
    private String email;
    private String mobileNo;
    private String gender;
    private LocalDate dateOfBirth;
    private Set<Role> roles;
}
