package com.himanism.hbooks.dto.response;

import com.himanism.hbooks.enums.LeadStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadResponseDTO {
    private Long id;

    private CourseResponseDTO interestedCourse;  // Nested DTO for Course to send details

    private CourseCategoryResponseDTO categorised; // Nested DTO for CourseCategory

    private String location;

    private String sourceOfEnquiry;

    private LeadStatus status;

    private String uniqueStudentId;
}
