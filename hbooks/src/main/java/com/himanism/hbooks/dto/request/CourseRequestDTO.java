package com.himanism.hbooks.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {
    @NotBlank(message = "Course name is mandatory")
    private String name;

    @NotBlank(message = "Course description is mandatory")
    private String description;

    @Min(value = 1, message = "Duration must be minimum 1 hour")
    private int durationHours;

    @NotNull(message = "Category ID is mandatory")
    private Long categoryId;  // Add this if your course has a category
}
