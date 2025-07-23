package com.himanism.hbooks.repository;

import com.himanism.hbooks.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    // Additional query methods if needed
}
