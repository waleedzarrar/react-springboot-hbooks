package com.himanism.hbooks.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseResponseDTO {
    private Long id;
    private String code;
    private String name;
    private String type;
    private String track;
    private String description;
    private Integer durationInHours;
    private BigDecimal price;
    private boolean isActive;
    private boolean isOnlineAvailable;
    private boolean isSlotBookingRequired;
    private boolean isCustomCourse;
    private List<String> tags;
    private CourseCategoryDTO category; // ✅ Nested object
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @Data
    public static class CourseCategoryDTO {
        private Long id;
        private String name;
        private String description;
    }
}
