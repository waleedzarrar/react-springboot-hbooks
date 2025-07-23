package com.himanism.hbooks.mapper;

import com.himanism.hbooks.dto.request.LeadRequestDTO;
import com.himanism.hbooks.dto.response.LeadResponseDTO;
import com.himanism.hbooks.entity.Course;
import com.himanism.hbooks.entity.CourseCategory;
import com.himanism.hbooks.entity.Lead;
import com.himanism.hbooks.repository.CourseCategoryRepository;
import com.himanism.hbooks.repository.CourseRepository;

import java.util.List;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, CourseCategoryMapper.class})
public abstract class LeadMapper {

    @Autowired
    protected CourseRepository courseRepository;

    @Autowired
    protected CourseCategoryRepository categoryRepository;

    @Mapping(target = "interestedCourse", expression = "java(fetchCourse(dto.getInterestedCourseId()))")
    @Mapping(target = "categorised", expression = "java(fetchCategory(dto.getCategorisedId()))")
    public abstract Lead toEntity(LeadRequestDTO dto);

    @Mapping(target = "interestedCourse", source = "interestedCourse")
    @Mapping(target = "categorised", source = "categorised")
    public abstract LeadResponseDTO toDto(Lead lead);  // <-- Map single Lead to LeadResponseDTO

    /**
     * This method should accept a List<Lead> to map to List<LeadResponseDTO>
     */
    public abstract List<LeadResponseDTO> toDtoList(List<Lead> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "interestedCourse", expression = "java(fetchCourse(dto.getInterestedCourseId()))")
    @Mapping(target = "categorised", expression = "java(fetchCategory(dto.getCategorisedId()))")
    public abstract void updateLeadFromDto(LeadRequestDTO dto, @MappingTarget Lead entity);

    protected Course fetchCourse(Long id) {
        if (id == null) {
            return null;
        }
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    protected CourseCategory fetchCategory(Long id) {
        if (id == null) {
            return null;
        }
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Category not found with id: " + id));
    }
}
