package com.himanism.hbooks.service;

import com.himanism.hbooks.dto.request.StudentProfileRequestDTO;
import com.himanism.hbooks.dto.response.StudentProfileResponseDTO;

public interface StudentProfileService {

    StudentProfileResponseDTO createOrUpdateStudentProfile(Long userId, StudentProfileRequestDTO dto);

    StudentProfileResponseDTO getStudentProfileByUserId(Long userId);
}
