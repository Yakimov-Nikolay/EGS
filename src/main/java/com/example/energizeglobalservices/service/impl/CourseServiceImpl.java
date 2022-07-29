package com.example.energizeglobalservices.service.impl;

import com.example.energizeglobalservices.model.dto.CollegeDTO;
import com.example.energizeglobalservices.model.dto.CourseDTO;
import com.example.energizeglobalservices.model.entity.CollegeEntity;
import com.example.energizeglobalservices.model.entity.CourseEntity;
import com.example.energizeglobalservices.repository.CollegeRepository;
import com.example.energizeglobalservices.repository.CourseRepository;
import com.example.energizeglobalservices.service.CollegeService;
import com.example.energizeglobalservices.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CollegeRepository collegeRepository;

    private final CollegeService collegeService;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, CollegeRepository collegeRepository, CollegeService collegeService, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.collegeRepository = collegeRepository;
        this.collegeService = collegeService;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        return courseRepository
                .findAll()
                .stream()
                .map(this::asCourse)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CourseDTO> getCourseById(long id) {
        return courseRepository
                .findById(id)
                .map(this::asCourse);
    }

    @Override
    public void deleteCourse(long courseId) {
        try {
            this.courseRepository.deleteById(courseId);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long createCourse(CourseDTO courseDTO) {

        CollegeEntity college = collegeRepository
                .findByName(courseDTO.getCollege().getName())
                .orElseGet(() -> new CollegeEntity()
                        .setName(courseDTO.getCollege().getName()));

        CourseEntity newCourse = new CourseEntity()
                .setCollege(college)
                .setTitle(courseDTO.getTitle());

        return courseRepository.save(newCourse).getId();
    }

    @Override
    public CourseDTO persistCourse(Long courseId, CourseDTO courseDTO) {
        Optional<CourseEntity> courseOpt = this.courseRepository.findById(courseId);
        if (courseOpt.isEmpty()) {
            return null;
        }
       CourseEntity courseEntity = courseOpt.get();

        updatedCourseEntity(courseEntity, courseDTO);
        this.courseRepository.save(courseEntity);
        return this.map(courseEntity);
    }

    @Override
    public Page<CourseDTO> getCourses(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return courseRepository.findAll(pageable).map(this::asCourse);
    }

    private void updatedCourseEntity(CourseEntity courseEntity, CourseDTO courseDTO) {
        String title = courseDTO.getTitle();
        if (title != null){
            courseEntity.setTitle(title);
        }

        if (courseDTO.getCollege() != null){
            Optional<CollegeEntity> collegeOpt =
                    this.collegeService
                            .findCollegeByName(courseDTO.getCollege().getName());

            courseEntity.setCollege(collegeOpt.isEmpty() ? this.collegeService
                    .save(new CollegeEntity().setName(courseDTO.getCollege().getName()))
                    : collegeOpt.get());
        }

    }

    private CourseDTO map(CourseEntity courseEntity) {
        return new
                CourseDTO().
                setId(courseEntity.getId())
                .setTitle(courseEntity.getTitle())
                .setCollege(new CollegeDTO().setName(courseEntity.getCollege().getName()));
    }


    private CourseDTO asCourse(CourseEntity course) {
        CourseDTO courseDTO = modelMapper.map(course, CourseDTO.class);
        CollegeDTO collegeDTO = modelMapper.map(course.getCollege(), CollegeDTO.class);

        courseDTO.setCollege(collegeDTO);

        return courseDTO;
    }
}
