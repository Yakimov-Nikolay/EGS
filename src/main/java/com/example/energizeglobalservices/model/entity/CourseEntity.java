package com.example.energizeglobalservices.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class CourseEntity extends BaseEntity{

    private String title;
    private CollegeEntity college;


    public String getTitle() {
        return title;
    }

    public CourseEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    public CollegeEntity getCollege() {
        return college;
    }

    public CourseEntity setCollege(CollegeEntity college) {
        this.college = college;
        return this;
    }

}
