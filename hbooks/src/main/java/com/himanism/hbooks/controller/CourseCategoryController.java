package com.himanism.hbooks.controller;

import com.himanism.hbooks.dto.request.CourseCategoryRequestDTO;
import com.himanism.hbooks.dto.response.CourseCategoryResponseDTO;
import com.himanism.hbooks.exception.ResourceNotFoundException;
import com.himanism.hbooks.service.CourseCategoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/course-categories")
@RequiredArgsConstructor
@Slf4j
public class CourseCategoryController {

    private final CourseCategoryService service;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CourseCategoryRequestDTO dto) {
        try {
            CourseCategoryResponseDTO response = service.createCategory(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating category: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        try {
            CourseCategoryResponseDTO category = service.getCategoryById(id);
            return ResponseEntity.ok(category);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching category: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        try {
            List<CourseCategoryResponseDTO> categories = service.getAllCategories();
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching categories: " + e.getMessage());
        }
    }
}
