package com.himanism.hbooks.mapper;


import com.himanism.hbooks.dto.request.CourseRequestDTO;
import com.himanism.hbooks.dto.response.CourseResponseDTO;
import com.himanism.hbooks.entity.Course;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    // Maps entity to response DTO
    CourseResponseDTO toDto(Course course);

    // Maps creation/update DTO to entity
    Course toEntity(CourseRequestDTO dto);

    // Update existing entity from DTO
    void updateEntityFromDto(CourseRequestDTO dto, @MappingTarget Course entity);

    // List mapping from entities to DTOs
    List<CourseResponseDTO> toDtoList(List<Course> courses);
}
