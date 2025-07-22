package com.himanism.hbooks.mapper;


import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;
import com.himanism.hbooks.entity.User;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toDto(User user);

    List<UserResponseDTO> toDtoList(List<User> users);

    User toEntity(UserRequestDTO userRequestDTO);

    void updateEntityFromDto(UserRequestDTO dto, @MappingTarget User entity);
}
