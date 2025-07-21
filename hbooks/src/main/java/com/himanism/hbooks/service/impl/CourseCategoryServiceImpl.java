package com.himanism.hbooks.service.impl;

import com.himanism.hbooks.dto.request.CourseCategoryRequestDTO;
import com.himanism.hbooks.dto.response.CourseCategoryResponseDTO;
import com.himanism.hbooks.entity.category.CourseCategory;
import com.himanism.hbooks.exception.ResourceNotFoundException;
import com.himanism.hbooks.mapper.CourseCategoryMapper;
import com.himanism.hbooks.repository.CourseCategoryRepository;
import com.himanism.hbooks.service.CourseCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseCategoryServiceImpl implements CourseCategoryService {

    private final CourseCategoryRepository repository;
    private final CourseCategoryMapper mapper;

    @Override
    public CourseCategoryResponseDTO createCategory(CourseCategoryRequestDTO dto) {
        CourseCategory entity = mapper.toEntity(dto);
        CourseCategory saved = repository.save(entity);
        return mapper.toResponseDto(saved);
    }

    @Override
    public CourseCategoryResponseDTO getCategoryById(Long id) {
        CourseCategory entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        return mapper.toResponseDto(entity);
    }

    @Override
    public List<CourseCategoryResponseDTO> getAllCategories() {
        List<CourseCategory> list = repository.findAll();
        return mapper.toResponseDtoList(list);
    }
}
