//package com.himanism.hbooks.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import java.time.LocalDateTime;
//
//import com.himanism.hbooks.enums.LeadStatus;
//
//@Entity
//@Table(name = "lead_comments")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class LeadComment {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lead_id", nullable = false)
//    private Lead lead;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "counselor_id", nullable = false)
//    private User counselor;
//
//    @Column(nullable = false, length = 1000)
//    private String comment;
//
//    @Enumerated(EnumType.STRING)
//    private LeadStatus leadStatus;
//
//    private LocalDateTime commentedAt;
//}
