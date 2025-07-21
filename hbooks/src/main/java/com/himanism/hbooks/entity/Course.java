package com.himanism.hbooks.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.himanism.hbooks.entity.category.CourseCategory;
import com.himanism.hbooks.entity.track.CourseTrack;
import com.himanism.hbooks.entity.type.CourseType;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 64)
    private String code;

    @Column(nullable = false, length = 255)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 32)
    private CourseType type;

    @Enumerated(EnumType.STRING)
    @Column(length = 32)
    private CourseTrack track;

    @Column(length = 1000)
    private String description;

    @Column(name = "duration_in_hours", nullable = false)
    private Integer durationInHours;

    private BigDecimal price;

    @Column(name = "is_active", nullable = false)
    private boolean isActive = true;  // defaults to true


    @Column(name = "is_online_available", nullable = false)
    private boolean isOnlineAvailable = false;

    @Column(name = "is_slot_booking_required", nullable = false)
    private boolean isSlotBookingRequired = false;

    @Column(name = "is_custom_course", nullable = false)
    private boolean isCustomCourse = false;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "course_tags", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "tag", length = 64)
    private List<String> tags;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CourseCategory category;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
