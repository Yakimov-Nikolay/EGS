package com.example.energizeglobalservices.service;

import com.example.energizeglobalservices.model.dto.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

public interface CourseService {

    List<CourseDTO> getAllCourses();

    Optional<CourseDTO> getCourseById(long id);

    void deleteCourse(long courseId);

    long createCourse(CourseDTO courseDTO);


    CourseDTO persistCourse(Long courseId, CourseDTO courseDTO);

    Page<CourseDTO> getCourses(
            int pageNo,
            int pageSize,
            String sortBy

    );

}
