package com.example.energizeglobalservices.service.impl;

import com.example.energizeglobalservices.model.entity.CollegeEntity;
import com.example.energizeglobalservices.repository.CollegeRepository;
import com.example.energizeglobalservices.service.CollegeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CollegeServiceImpl implements CollegeService {

    private final CollegeRepository collegeRepository;

    public CollegeServiceImpl(CollegeRepository collegeRepository) {
        this.collegeRepository = collegeRepository;
    }

    public Optional<CollegeEntity> findCollegeByName(String name) {
        return this.collegeRepository.findByName(name);
    }

    @Override
    public CollegeEntity save(CollegeEntity newName) {
        return this.collegeRepository.save(newName);
    }
}
