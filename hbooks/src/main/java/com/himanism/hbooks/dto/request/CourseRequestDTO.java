package com.himanism.hbooks.dto.request;

import com.himanism.hbooks.entity.CourseTrack;
import com.himanism.hbooks.entity.CourseType;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {

    @NotBlank(message = "Course code is mandatory")
    private String code;

    @NotBlank(message = "Course name is mandatory")
    private String name;

    @NotNull(message = "Course type is mandatory")
    private CourseType type;

    @NotNull(message = "Course track is mandatory")
    private CourseTrack track;

    @NotBlank(message = "Course description is mandatory")
    private String description;

    @Min(value = 1, message = "Duration must be minimum 1 hour")
    private int durationInHours;

    @DecimalMin(value = "0.0", inclusive = true, message = "Price cannot be negative")
    private BigDecimal price;

    private boolean isActive = true;

    private boolean isOnlineAvailable = false;

    private boolean isSlotBookingRequired = false;

    private boolean isCustomCourse = false;

    private List<String> tags;

    @NotNull(message = "Category ID is mandatory")
    private Long category;
}
