package com.himanism.hbooks.mapper;

import com.himanism.hbooks.dto.UserWithStudentProfileDTO;
import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;

import com.himanism.hbooks.dto.response.StudentProfileResponseDTO;
import com.himanism.hbooks.entity.RoleEntity;
import com.himanism.hbooks.entity.User;
import com.himanism.hbooks.entity.StudentProfile;
import com.himanism.hbooks.enums.Role;
import com.himanism.hbooks.repository.RoleRepository;
import com.himanism.hbooks.service.helper.UserServiceHelper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {StudentProfileMapper.class})
public abstract class UserMapper {

    @Autowired
    protected RoleRepository roleRepository;

    @Autowired
    protected UserServiceHelper userServiceHelper;

    // Map User to UserResponseDTO with fullName computed
    @Mapping(target = "fullName", expression = "java(userServiceHelper.generateFullName(user))")
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoleEntitiesToRoles")
    public abstract UserResponseDTO toDto(User user);

    public abstract List<UserResponseDTO> toDtoList(List<User> users);

    // Map UserRequestDTO to User entity (roles mapped)
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToRoleEntities")
    public abstract User toEntity(UserRequestDTO userRequestDTO);

    // Update User entity from UserRequestDTO
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToRoleEntities")
    public abstract void updateEntityFromDto(UserRequestDTO dto, @MappingTarget User entity);

    // Map User and StudentProfile together to combined DTO (nested studentProfile)
    @Mapping(target = "studentProfile", source = "studentProfile")
    @Mapping(target = "fullName", expression = "java(userServiceHelper.generateFullName(user))")
    @Mapping(target = "roles", source = "user.roles", qualifiedByName = "mapRoleEntitiesToRoles")
    public abstract UserWithStudentProfileDTO toUserWithStudentProfileDto(User user, StudentProfile studentProfile);

    // RoleEntity Set --> Role Set conversion
    @Named("mapRoleEntitiesToRoles")
    public Set<Role> mapRoleEntitiesToRoles(Set<RoleEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(RoleEntity::getName)  // Assumes getName() returns Role enum
                .collect(Collectors.toSet());
    }

    // Role Set --> RoleEntity Set conversion
    @Named("mapRolesToRoleEntities")
    public Set<RoleEntity> mapRolesToRoleEntities(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(roleEnum -> roleRepository.findByName(roleEnum)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleEnum)))
                .collect(Collectors.toSet());
    }
}
