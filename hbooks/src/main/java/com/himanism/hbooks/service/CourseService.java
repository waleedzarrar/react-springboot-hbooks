package com.himanism.hbooks.service;

import com.himanism.hbooks.entity.Course;
import com.himanism.hbooks.exception.ResourceNotFoundException;
import com.himanism.hbooks.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(Course course){
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id){
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id " + id));
    }

    public Course updateCourse(Long id, Course updatedCourse){
        Course course = getCourseById(id);
        course.setTitle(updatedCourse.getTitle());
        course.setDescription(updatedCourse.getDescription());
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id){
        Course course = getCourseById(id);
        courseRepository.delete(course);
    }
}
