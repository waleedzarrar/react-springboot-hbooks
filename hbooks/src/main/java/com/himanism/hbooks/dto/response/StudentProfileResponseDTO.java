package com.himanism.hbooks.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfileResponseDTO {

    private String academicHistory;

    private BigDecimal feesDue;

    private BigDecimal feesPaid;

    private Integer attendancePercentage;

    private String progressDetails;
}
