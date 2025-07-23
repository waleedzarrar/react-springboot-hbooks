package com.himanism.hbooks.mapper;

import com.himanism.hbooks.dto.request.CourseRequestDTO;
import com.himanism.hbooks.dto.response.CourseResponseDTO;
import com.himanism.hbooks.entity.Course;
import com.himanism.hbooks.entity.CourseCategory;
import com.himanism.hbooks.repository.CourseCategoryRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class CourseMapper {

    @Autowired
    protected CourseCategoryRepository categoryRepository;

    // DTO → Entity
    @Mapping(target = "category", ignore = true)
    public abstract Course toEntity(CourseRequestDTO dto);

    @AfterMapping
    protected void setCategoryAfterMapping(CourseRequestDTO dto, @MappingTarget Course course) {
        if (dto.getCategory() != null) {
            course.setCategory(categoryRepository.findById(dto.getCategory())
                    .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategory())));
        }
    }

    // Entity → DTO
    @Mapping(target = "category", source = "category")
    public abstract CourseResponseDTO toDto(Course course);

    // Map nested category
    public abstract CourseResponseDTO.CourseCategoryDTO toDto(CourseCategory category);

    public abstract List<CourseResponseDTO> toDtoList(List<Course> courses);

    // Update
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "category", ignore = true)
    public abstract void updateEntityFromDto(CourseRequestDTO dto, @MappingTarget Course entity);

    @AfterMapping
    protected void updateCategoryAfterMapping(CourseRequestDTO dto, @MappingTarget Course entity) {
        if (dto.getCategory() != null) {
            entity.setCategory(categoryRepository.findById(dto.getCategory())
                    .orElseThrow(() -> new RuntimeException("Category not found: " + dto.getCategory())));
        }
    }
}
