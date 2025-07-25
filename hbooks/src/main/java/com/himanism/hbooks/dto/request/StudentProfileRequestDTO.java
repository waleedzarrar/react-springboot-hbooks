package com.himanism.hbooks.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfileRequestDTO {

    private String academicHistory;

    private BigDecimal feesDue;

    private BigDecimal feesPaid;

    private Integer attendancePercentage;

    private String progressDetails;
}
