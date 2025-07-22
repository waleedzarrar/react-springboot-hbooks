package com.himanism.hbooks.mapper;

import com.himanism.hbooks.dto.request.UserRequestDTO;
import com.himanism.hbooks.dto.response.UserResponseDTO;
import com.himanism.hbooks.entity.RoleEntity;
import com.himanism.hbooks.entity.User;
import com.himanism.hbooks.enums.Role;
import com.himanism.hbooks.repository.RoleRepository;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected RoleRepository roleRepository;

    @Mapping(target = "fullName", ignore = true) // Ignore fullName to set manually later in service
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRoleEntitiesToRoles")
    public abstract UserResponseDTO toDto(User user);

    public abstract List<UserResponseDTO> toDtoList(List<User> users);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToRoleEntities")
    public abstract User toEntity(UserRequestDTO userRequestDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToRoleEntities")
    public abstract void updateEntityFromDto(UserRequestDTO dto, @MappingTarget User entity);

    @Named("mapRoleEntitiesToRoles")
    public Set<Role> mapRoleEntitiesToRoles(Set<RoleEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(RoleEntity::getName)
                .collect(Collectors.toSet());
    }

    @Named("mapRolesToRoleEntities")
    public Set<RoleEntity> mapRolesToRoleEntities(Set<Role> roles) {
        if (roles == null) return null;
        return roles.stream()
                .map(roleEnum ->
                    roleRepository.findByName(roleEnum)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleEnum))
                )
                .collect(Collectors.toSet());
    }
}
