package com.himanism.hbooks.service.impl;

import com.himanism.hbooks.dto.request.StudentProfileRequestDTO;
import com.himanism.hbooks.dto.response.StudentProfileResponseDTO;
import com.himanism.hbooks.entity.StudentProfile;
import com.himanism.hbooks.entity.User;
import com.himanism.hbooks.exception.UserNotFoundException;
import com.himanism.hbooks.mapper.StudentProfileMapper;
import com.himanism.hbooks.repository.StudentProfileRepository;
import com.himanism.hbooks.repository.UserRepository;
import com.himanism.hbooks.service.StudentProfileService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentProfileServiceImpl implements StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;
    private final UserRepository userRepository;
    private final StudentProfileMapper studentProfileMapper;

    @Override
    @Transactional
    public StudentProfileResponseDTO createOrUpdateStudentProfile(Long userId, StudentProfileRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        StudentProfile profile = studentProfileRepository.findById(userId)
                .orElse(StudentProfile.builder().user(user).build());

        studentProfileMapper.updateEntityFromDto(dto, profile);

        profile.setUser(user); // ensure linkage

        StudentProfile saved = studentProfileRepository.save(profile);

        return studentProfileMapper.toDto(saved);
    }

    @Override
    public StudentProfileResponseDTO getStudentProfileByUserId(Long userId) {
        return studentProfileRepository.findById(userId)
                .map(studentProfileMapper::toDto)
                .orElse(null);
    }
}
