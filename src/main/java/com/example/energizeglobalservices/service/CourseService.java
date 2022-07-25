package com.example.energizeglobalservices.service;

import com.example.energizeglobalservices.model.dto.CourseDTO;

import java.util.*;

public interface CourseService {

    List<CourseDTO> getAllCourses();

    Optional<CourseDTO> getCourseById(long id);
    void deleteCourse(long courseId);

    long createCourse(CourseDTO courseDTO);


    CourseDTO persistCourse(Long courseId, CourseDTO courseDTO);

}
