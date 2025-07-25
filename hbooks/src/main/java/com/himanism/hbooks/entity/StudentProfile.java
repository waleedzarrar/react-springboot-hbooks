package com.himanism.hbooks.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "student_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfile {

    @Id
    private Long userId;  // Primary key same as User id (Maps to User)

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(columnDefinition = "TEXT")
    private String academicHistory;  // You could replace with a structured entity later

    private BigDecimal feesDue;

    private BigDecimal feesPaid;

    private Integer attendancePercentage; // 0 - 100

    @Column(columnDefinition = "TEXT")
    private String progressDetails;
}
