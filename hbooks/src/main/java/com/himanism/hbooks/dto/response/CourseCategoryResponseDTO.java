package com.himanism.hbooks.dto.response;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCategoryResponseDTO {

    private Long id;
    private String name;
    private String description;
}