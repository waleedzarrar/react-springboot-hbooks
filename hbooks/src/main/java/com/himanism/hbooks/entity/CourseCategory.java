package com.himanism.hbooks.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "course_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 64)
    private String name;

    @Column(length = 255)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<Course> courses;
}