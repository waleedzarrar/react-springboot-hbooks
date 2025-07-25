package com.himanism.hbooks.controller;

import com.himanism.hbooks.dto.request.StudentProfileRequestDTO;
import com.himanism.hbooks.dto.response.StudentProfileResponseDTO;
import com.himanism.hbooks.service.StudentProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/studentProfiles")
@RequiredArgsConstructor
public class StudentProfileController {

    private final StudentProfileService studentProfileService;

    @PostMapping("/{userId}")
    public ResponseEntity<StudentProfileResponseDTO> createOrUpdateProfile(
            @PathVariable Long userId,
            @Valid @RequestBody StudentProfileRequestDTO dto) {

        StudentProfileResponseDTO responseDTO = studentProfileService.createOrUpdateStudentProfile(userId, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<StudentProfileResponseDTO> getProfile(@PathVariable Long userId) {
        StudentProfileResponseDTO dto = studentProfileService.getStudentProfileByUserId(userId);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
}
