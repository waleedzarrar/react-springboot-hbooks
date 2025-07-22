package com.himanism.hbooks.service;



import java.util.List;

import com.himanism.hbooks.dto.request.CourseCategoryRequestDTO;
import com.himanism.hbooks.dto.response.CourseCategoryResponseDTO;

public interface CourseCategoryService {
    List<CourseCategoryResponseDTO> getAllCategories();
    CourseCategoryResponseDTO getCategoryById(Long id);
    CourseCategoryResponseDTO createCategory(CourseCategoryRequestDTO dto);
    CourseCategoryResponseDTO updateCategory(Long id, CourseCategoryRequestDTO dto);
    void deleteCategoryById(Long id);
}
