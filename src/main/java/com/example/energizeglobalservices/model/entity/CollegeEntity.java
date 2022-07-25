package com.example.energizeglobalservices.model.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "college")
public class CollegeEntity extends BaseEntity{
    private String name;
    private List<CourseEntity> courses = new LinkedList<>();

    public String getName() {
        return name;
    }

    public CollegeEntity setName(String name) {
        this.name = name;
        return this;
    }
    @OneToMany(mappedBy = "college",fetch = FetchType.EAGER)
    public List<CourseEntity> getCourses() {
        return courses;
    }

    public CollegeEntity setCourses(List<CourseEntity> courses) {
        this.courses = courses;
        return this;
    }

    public CollegeEntity addCourses(CourseEntity course){
        this.courses.add(course);
        return this;
    }


}
