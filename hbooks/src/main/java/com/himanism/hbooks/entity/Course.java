package com.himanism.hbooks.entity;

import jakarta.persistence.*;

@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String title;

    @Column(length=2000)
    private String description;

    public Course() {}

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getters and setters:

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
}
