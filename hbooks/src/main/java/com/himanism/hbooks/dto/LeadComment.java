//// --- LeadComment.java (NEW)
//package com.himanism.hbooks.dto;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//
//import com.himanism.hbooks.entity.Lead;
//import com.himanism.hbooks.entity.User;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class LeadComment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "lead_id")
//    private Lead lead;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "commented_by")
//    private User commentedBy;
//
//    private String commentText;
//    private LocalDateTime createdAt;
//}