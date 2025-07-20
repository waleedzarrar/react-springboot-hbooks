package com.himanism.hbooks.repository;

import com.himanism.hbooks.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
