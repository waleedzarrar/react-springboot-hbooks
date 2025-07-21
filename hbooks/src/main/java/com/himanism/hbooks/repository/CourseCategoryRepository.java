package com.himanism.hbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himanism.hbooks.entity.category.CourseCategory;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
}
