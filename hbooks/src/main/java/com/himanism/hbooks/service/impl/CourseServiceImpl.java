package com.himanism.hbooks.service.impl;

import com.himanism.hbooks.dto.request.CourseRequestDTO;
import com.himanism.hbooks.dto.response.CourseResponseDTO;
import com.himanism.hbooks.entity.Course;
import com.himanism.hbooks.entity.category.CourseCategory;
import com.himanism.hbooks.exception.ResourceNotFoundException;
import com.himanism.hbooks.mapper.CourseMapper;
import com.himanism.hbooks.repository.CourseCategoryRepository;
import com.himanism.hbooks.repository.CourseRepository;
import com.himanism.hbooks.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseCategoryRepository categoryRepository;
    private final CourseMapper mapper;

    @Override
    @Transactional
    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        Course course = mapper.toEntity(dto);

        if (dto.getCategoryId() != null) {
            CourseCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));
            course.setCategory(category);
        }

        Course saved = courseRepository.save(course);
        return mapper.toResponseDto(saved);
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return mapper.toResponseDto(course);
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return mapper.toResponseDtoList(courses);
    }

    @Override
    @Transactional
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        mapper.updateCourseFromDto(dto, course);

        if (dto.getCategoryId() != null) {
            CourseCategory category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + dto.getCategoryId()));
            course.setCategory(category);
        } else {
            course.setCategory(null);
        }
        Course updated = courseRepository.save(course);
        return mapper.toResponseDto(updated);
    }

    @Override
    public boolean deleteCourseById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
        return true;
    }
}
