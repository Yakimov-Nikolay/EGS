package com.example.energizeglobalservices;

import com.example.energizeglobalservices.model.entity.CollegeEntity;
import com.example.energizeglobalservices.model.entity.CourseEntity;
import com.example.energizeglobalservices.repository.CollegeRepository;
import com.example.energizeglobalservices.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CourseApplicationInit implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final CollegeRepository collegeRepository;

    public CourseApplicationInit(CourseRepository courseRepository, CollegeRepository collegeRepository) {
        this.courseRepository = courseRepository;
        this.collegeRepository = collegeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (courseRepository.count() == 0 && collegeRepository.count() == 0) {
            initUniversityOfForestry();
            initTechnicalUniversity();
            initSofiaUniversity();
            initUniversityOfNationalAndWorldEconomy();
            initNationalSportsAcademy();

        }
    }

    private void initNationalSportsAcademy() {
        initCollege("National Sports Academy", "Football", "Basketball",
                "Badminton");
    }

    private void initUniversityOfNationalAndWorldEconomy() {
        initCollege("University of National and World Economy", "Economy", "Political Sciences",
                "Administration and Management");
    }

    private void initSofiaUniversity() {
        initCollege("Sofia University", "History", "Mathematics", "Law");
    }

    private void initTechnicalUniversity() {
        initCollege("Technical University", "Electrical engineering", "Mechatronics",
                "Engineering design");
    }

    private void initUniversityOfForestry() {
        initCollege("University of Forestry", "Agronomy", "Forestry", "Business Administration",
                "Veterinary Medicine");
    }

    private void initCollege(String collegeName, String... courses) {
        CollegeEntity college = new CollegeEntity();
        college.setName(collegeName);

        List<CourseEntity> allCourses = new ArrayList<>();

        for (String course : courses) {
            CourseEntity aCourse = new CourseEntity();
            aCourse.setCollege(college);
            aCourse.setTitle(course);
            allCourses.add(aCourse);
        }
        college.setCourses(allCourses);

        courseRepository.saveAll(allCourses);

    }
}
