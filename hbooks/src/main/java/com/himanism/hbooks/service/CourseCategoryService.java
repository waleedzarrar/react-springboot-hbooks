package com.himanism.hbooks.service;

import com.himanism.hbooks.dto.request.CourseCategoryRequestDTO;
import com.himanism.hbooks.dto.response.CourseCategoryResponseDTO;

import java.util.List;

public interface CourseCategoryService {

    CourseCategoryResponseDTO createCategory(CourseCategoryRequestDTO dto);

    CourseCategoryResponseDTO getCategoryById(Long id);

    List<CourseCategoryResponseDTO> getAllCategories();
}
