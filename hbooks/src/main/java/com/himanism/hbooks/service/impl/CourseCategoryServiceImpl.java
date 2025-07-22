package com.himanism.hbooks.service.impl;

import com.himanism.hbooks.dto.request.CourseCategoryRequestDTO;
import com.himanism.hbooks.dto.response.CourseCategoryResponseDTO;
import com.himanism.hbooks.entity.CourseCategory;
import com.himanism.hbooks.mapper.CourseCategoryMapper;
import com.himanism.hbooks.repository.CourseCategoryRepository;
import com.himanism.hbooks.service.CourseCategoryService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryRepository courseCategoryRepository;
    private final CourseCategoryMapper courseCategoryMapper;

    public CourseCategoryServiceImpl(CourseCategoryRepository courseCategoryRepository,
                                     CourseCategoryMapper courseCategoryMapper) {
        this.courseCategoryRepository = courseCategoryRepository;
        this.courseCategoryMapper = courseCategoryMapper;
    }

    @Override
    public List<CourseCategoryResponseDTO> getAllCategories() {
        List<CourseCategory> categories = courseCategoryRepository.findAll();
        return courseCategoryMapper.toDtoList(categories);
    }

    @Override
    public CourseCategoryResponseDTO getCategoryById(Long id) {
        CourseCategory category = courseCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return courseCategoryMapper.toDto(category);
    }

    @Override
    public CourseCategoryResponseDTO createCategory(CourseCategoryRequestDTO dto) {
        CourseCategory category = courseCategoryMapper.toEntity(dto);
        category = courseCategoryRepository.save(category);
        return courseCategoryMapper.toDto(category);
    }

    @Override
    public CourseCategoryResponseDTO updateCategory(Long id, CourseCategoryRequestDTO dto) {
        CourseCategory existing = courseCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        courseCategoryMapper.updateEntityFromDto(dto, existing);
        existing = courseCategoryRepository.save(existing);
        return courseCategoryMapper.toDto(existing);
    }

    @Override
    public void deleteCategoryById(Long id) {
        courseCategoryRepository.deleteById(id);
    }
}
