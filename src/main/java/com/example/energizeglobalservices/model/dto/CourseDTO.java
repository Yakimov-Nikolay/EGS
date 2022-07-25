package com.example.energizeglobalservices.model.dto;

public class CourseDTO {

    private CollegeDTO college;
    private Long id;
    private String title;


    public CollegeDTO getCollege() {
        return college;
    }

    public CourseDTO setCollege(CollegeDTO college) {
        this.college = college;
        return this;
    }

    public Long getId() {
        return id;
    }

    public CourseDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseDTO setTitle(String title) {
        this.title = title;
        return this;
    }
}
