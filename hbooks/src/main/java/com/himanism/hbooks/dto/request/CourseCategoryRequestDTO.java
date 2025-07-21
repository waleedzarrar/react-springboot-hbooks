package com.himanism.hbooks.dto.request;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCategoryRequestDTO {
    private String name;
    private String description;
}
