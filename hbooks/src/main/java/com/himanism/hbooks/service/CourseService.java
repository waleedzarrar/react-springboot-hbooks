package com.himanism.hbooks.service;

import com.himanism.hbooks.dto.request.CourseRequestDTO;
import com.himanism.hbooks.dto.response.CourseResponseDTO;

import java.util.List;

public interface CourseService {
    CourseResponseDTO createCourse(CourseRequestDTO dto);

    CourseResponseDTO getCourseById(Long id);

    List<CourseResponseDTO> getAllCourses();

    CourseResponseDTO updateCourse(Long id, CourseRequestDTO dto);

    boolean deleteCourseById(Long id);
}
