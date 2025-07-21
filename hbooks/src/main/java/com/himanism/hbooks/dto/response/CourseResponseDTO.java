package com.himanism.hbooks.dto.response;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.himanism.hbooks.entity.track.CourseTrack;
import com.himanism.hbooks.entity.type.CourseType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponseDTO {

    private Long id;
    private String code;
    private String name;
    private CourseType type;
    private CourseTrack track;
    private String description;
    private Integer durationInHours;
    private BigDecimal price;
    private boolean isActive;
    private boolean isOnlineAvailable;
    private boolean isSlotBookingRequired;
    private boolean isCustomCourse;
    private List<String> tags;
    private CourseCategoryDTO category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CourseCategoryDTO {
        private Long id;
        private String name;
        private String description;
    }
}
