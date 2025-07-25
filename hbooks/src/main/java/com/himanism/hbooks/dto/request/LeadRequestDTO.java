package com.himanism.hbooks.dto.request;

import com.himanism.hbooks.enums.LeadStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadRequestDTO {

    @NotNull
    private Long interestedCourseId;     // Reference to Course.id

    private Long categorisedId;           // Reference to CourseCategory.id

    private String location;

    @NotBlank
    private String sourceOfEnquiry;

    @NotNull
    private LeadStatus status;

    @NotNull
    private Long userId;  // updated from uniqueStudentId to userId (Long)
}
