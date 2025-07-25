package com.himanism.hbooks.repository;

import com.himanism.hbooks.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    // Additional queries as needed
}
