package com.himanism.hbooks.mapper;


import com.himanism.hbooks.dto.request.CourseCategoryRequestDTO;
import com.himanism.hbooks.dto.response.CourseCategoryResponseDTO;
import com.himanism.hbooks.entity.CourseCategory;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseCategoryMapper {

    CourseCategoryResponseDTO toDto(CourseCategory category);

    List<CourseCategoryResponseDTO> toDtoList(List<CourseCategory> categories);

    CourseCategory toEntity(CourseCategoryRequestDTO dto);

    void updateEntityFromDto(CourseCategoryRequestDTO dto, @MappingTarget CourseCategory entity);
}