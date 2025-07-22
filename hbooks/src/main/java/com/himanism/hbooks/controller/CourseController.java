package com.himanism.hbooks.controller;

import com.himanism.hbooks.dto.request.CourseRequestDTO;
import com.himanism.hbooks.dto.response.CourseResponseDTO;
import com.himanism.hbooks.service.CourseService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELLOR','FACULTY')")
    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO dto) {
        CourseResponseDTO response = service.createCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELLOR','FACULTY','STUDENT')")
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        CourseResponseDTO course = service.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELLOR','FACULTY','STUDENT')")
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> courses = service.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @PreAuthorize("hasAnyRole('ADMIN','COUNSELLOR')")
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id, @Valid @RequestBody CourseRequestDTO dto) {
        CourseResponseDTO updated = service.updateCourse(id, dto);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        service.deleteCourseById(id);
        return ResponseEntity.ok("Course deleted successfully");
    }
}
