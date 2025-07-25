package com.himanism.hbooks.mapper;

import com.himanism.hbooks.dto.request.StudentProfileRequestDTO;
import com.himanism.hbooks.dto.response.StudentProfileResponseDTO;
import com.himanism.hbooks.entity.StudentProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentProfileMapper {

    StudentProfile toEntity(StudentProfileRequestDTO dto);

    StudentProfileResponseDTO toDto(StudentProfile entity);

    List<StudentProfileResponseDTO> toDtoList(List<StudentProfile> entities);

    void updateEntityFromDto(StudentProfileRequestDTO dto, @MappingTarget StudentProfile entity);

}
