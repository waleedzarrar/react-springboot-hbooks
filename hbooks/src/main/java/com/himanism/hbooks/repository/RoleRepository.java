package com.himanism.hbooks.repository;

import com.himanism.hbooks.entity.RoleEntity;
import com.himanism.hbooks.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(Role name);
}
