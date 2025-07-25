package com.himanism.hbooks.mapper;

import com.himanism.hbooks.dto.request.LeadRequestDTO;
import com.himanism.hbooks.dto.response.LeadResponseDTO;
import com.himanism.hbooks.entity.Course;
import com.himanism.hbooks.entity.CourseCategory;
import com.himanism.hbooks.entity.Lead;
import com.himanism.hbooks.entity.User; // Import User entity
import com.himanism.hbooks.repository.CourseCategoryRepository;
import com.himanism.hbooks.repository.CourseRepository;
import com.himanism.hbooks.repository.UserRepository; // Import UserRepository

import java.util.List;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

// Add UserMapper to uses to map User entity to UserResponseDTO
@Mapper(componentModel = "spring", uses = {CourseMapper.class, CourseCategoryMapper.class, UserMapper.class})
public abstract class LeadMapper {

    @Autowired
    protected CourseRepository courseRepository;

    @Autowired
    protected CourseCategoryRepository categoryRepository;

    @Autowired
    protected UserRepository userRepository; // Autowire UserRepository

    @Mapping(target = "interestedCourse", expression = "java(fetchCourse(dto.getInterestedCourseId()))")
    @Mapping(target = "categorised", expression = "java(fetchCategory(dto.getCategorisedId()))")
    @Mapping(target = "user", expression = "java(fetchUser(dto.getUserId()))") // Map userId to User entity
    public abstract Lead toEntity(LeadRequestDTO dto);

    @Mapping(target = "interestedCourse", source = "interestedCourse")
    @Mapping(target = "categorised", source = "categorised")
    @Mapping(target = "user", source = "user") // Map User entity to UserResponseDTO
    public abstract LeadResponseDTO toDto(Lead lead);

    public abstract List<LeadResponseDTO> toDtoList(List<Lead> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "interestedCourse", expression = "java(fetchCourse(dto.getInterestedCourseId()))")
    @Mapping(target = "categorised", expression = "java(fetchCategory(dto.getCategorisedId()))")
    @Mapping(target = "user", expression = "java(fetchUser(dto.getUserId()))") // Update User entity
    public abstract void updateLeadFromDto(LeadRequestDTO dto, @MappingTarget Lead entity);

    protected Course fetchCourse(Long id) {
        if (id == null) {
            return null;
        }
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id)); // Consider custom exception
    }

    protected CourseCategory fetchCategory(Long id) {
        if (id == null) {
            return null;
        }
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course Category not found with id: " + id)); // Consider custom exception
    }

    // New method to fetch User entity by ID
    protected User fetchUser(Long id) {
        if (id == null) {
            return null;
        }
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id)); // Consider UserNotFoundException
    }
}
