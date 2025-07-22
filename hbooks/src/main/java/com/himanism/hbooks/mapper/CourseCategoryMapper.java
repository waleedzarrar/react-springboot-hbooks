package com.himanism.hbooks.mapper;

import com.himanism.hbooks.dto.request.CourseCategoryRequestDTO;
import com.himanism.hbooks.dto.response.CourseCategoryResponseDTO;
import com.himanism.hbooks.entity.CourseCategory;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseCategoryMapper {

    CourseCategory toEntity(CourseCategoryRequestDTO dto);

    CourseCategoryResponseDTO toResponseDto(CourseCategory entity);

    List<CourseCategoryResponseDTO> toResponseDtoList(List<CourseCategory> entities);
}
