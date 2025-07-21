package com.himanism.hbooks.mapper;

import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;
import com.himanism.hbooks.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Map Entity to Response DTO
    UserResponseDTO toResponseDto(User user);

    // Map Request DTO to Entity for creation
    User toEntity(UserRequestDTO dto);

    // Update existing entity fields from request DTO during updates
    void updateUserFromDto(UserRequestDTO dto, @MappingTarget User entity);

    // Map list of entities to list of response DTOs
    List<UserResponseDTO> toResponseDtoList(List<User> users);
}
