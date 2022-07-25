package com.example.energizeglobalservices.web;

import com.example.energizeglobalservices.model.dto.CourseDTO;
import com.example.energizeglobalservices.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> allCourses = courseService.getAllCourses();

        return ResponseEntity.ok(allCourses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable("id") Long id) {
        Optional<CourseDTO> course = courseService.getCourseById(id);
        if (course.isEmpty()) {
            return ResponseEntity
                    .notFound()
                    .build();
        } else {
            return ResponseEntity
                    .ok(course.get());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CourseDTO> deleteCourse(@PathVariable("id") Long courseId) {
        courseService.deleteCourse(courseId);

        return ResponseEntity
                .noContent()
                .build();
    }

    @PostMapping ()
    public ResponseEntity<CourseDTO> create(@RequestBody CourseDTO courseDTO
    , UriComponentsBuilder uriComponentsBuilder) {

        long courseId = courseService.createCourse(courseDTO);

        URI location = uriComponentsBuilder
                .path("/courses/{id}")
                .buildAndExpand(courseId).toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> update(@PathVariable("id") Long courseId,
                                            @RequestBody CourseDTO courseDTO){
       CourseDTO updatesCourseDTO = this.courseService.persistCourse(courseId, courseDTO);

       if (updatesCourseDTO == null){
           return ResponseEntity.notFound().build();
       }else{
           return ResponseEntity.ok(updatesCourseDTO);
       }
    }
}
