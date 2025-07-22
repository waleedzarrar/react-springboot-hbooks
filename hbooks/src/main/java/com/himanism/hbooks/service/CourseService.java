package com.himanism.hbooks.service;



import java.util.List;

import com.himanism.hbooks.dto.request.CourseRequestDTO;
import com.himanism.hbooks.dto.response.CourseResponseDTO;

public interface CourseService {
    List<CourseResponseDTO> getAllCourses();
    CourseResponseDTO getCourseById(Long id);
    CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO);
    CourseResponseDTO updateCourse(Long id, CourseRequestDTO courseRequestDTO);
    void deleteCourseById(Long id);
}
