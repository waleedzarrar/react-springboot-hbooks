package com.himanism.hbooks.mapper;

import com.himanism.hbooks.dto.request.CourseRequestDTO;
import com.himanism.hbooks.dto.response.CourseResponseDTO;
import com.himanism.hbooks.entity.Course;
import com.himanism.hbooks.entity.CourseCategory;

import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface CourseMapper {

    @Mapping(target = "category", expression = "java(toCategoryEntity(dto.getCategoryId()))")
    Course toEntity(CourseRequestDTO dto);

    @Mapping(target = "category", source = "category")
    CourseResponseDTO toResponseDto(Course entity);

    List<CourseResponseDTO> toResponseDtoList(List<Course> entities);

    @Mapping(target = "category", expression = "java(toCategoryEntity(dto.getCategoryId()))")
    void updateCourseFromDto(CourseRequestDTO dto, @MappingTarget Course entity);

    default CourseCategory toCategoryEntity(Long categoryId) {
        if (categoryId == null) return null;
        CourseCategory category = new CourseCategory();
        category.setId(categoryId);
        return category;
    }

    default CourseResponseDTO.CourseCategoryDTO toCategoryDTO(CourseCategory category) {
        if (category == null) return null;
        return new CourseResponseDTO.CourseCategoryDTO(category.getId(), category.getName(), category.getDescription());
    }
}
