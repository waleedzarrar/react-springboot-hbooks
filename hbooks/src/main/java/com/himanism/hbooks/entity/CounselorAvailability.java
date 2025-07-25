//// --- CounselorAvailability.java (NEW)
//package com.himanism.hbooks.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class CounselorAvailability {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "counselor_id")
//    private User counselor;
//
//    private boolean isAvailable;
//    private int leadsHandled;
//    private int leadsClosed;
//}