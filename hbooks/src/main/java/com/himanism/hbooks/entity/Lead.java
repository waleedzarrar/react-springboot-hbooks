package com.himanism.hbooks.entity;

import com.himanism.hbooks.enums.LeadStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "leads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link interested Course to Course entity (Many Leads can have same Course)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interested_course_id", nullable = false)
    private Course interestedCourse;

    // Link categorised to CourseCategory entity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_category_id")
    private CourseCategory categorised;

    private String location;

    private String sourceOfEnquiry;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    private String uniqueStudentId;
}
