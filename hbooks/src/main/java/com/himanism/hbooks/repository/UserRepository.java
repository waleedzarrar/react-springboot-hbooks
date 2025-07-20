package com.himanism.hbooks.repository;

import com.himanism.hbooks.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
