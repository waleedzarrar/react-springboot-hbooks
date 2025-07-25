package com.himanism.hbooks.dto;

import java.time.LocalDate;
import java.util.Set;

import com.himanism.hbooks.dto.response.StudentProfileResponseDTO;
import com.himanism.hbooks.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithStudentProfileDTO {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private String email;
    private String mobileNo;
    private String gender;
    private LocalDate dateOfBirth;
    private Set<Role> roles;
    private StudentProfileResponseDTO studentProfile;
}

