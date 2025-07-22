package com.himanism.hbooks.service.impl;

import com.himanism.hbooks.dto.request.CourseRequestDTO;
import com.himanism.hbooks.dto.response.CourseResponseDTO;
import com.himanism.hbooks.entity.Course;
import com.himanism.hbooks.mapper.CourseMapper;
import com.himanism.hbooks.repository.CourseRepository;
import com.himanism.hbooks.service.CourseService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return courseMapper.toDto(course);   // this requires toDto(Course) method
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courseMapper.toDtoList(courses);
    }

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO dto) {
        Course course = courseMapper.toEntity(dto);
        course = courseRepository.save(course);
        return courseMapper.toDto(course);
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        courseMapper.updateEntityFromDto(dto, existing);
        existing = courseRepository.save(existing);
        return courseMapper.toDto(existing);
    }

    @Override
    public void deleteCourseById(Long id) {
        courseRepository.deleteById(id);
    }
}


