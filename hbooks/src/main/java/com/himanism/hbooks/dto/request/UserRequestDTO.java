package com.himanism.hbooks.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String gender;
    private LocalDate dateOfBirth;
}
