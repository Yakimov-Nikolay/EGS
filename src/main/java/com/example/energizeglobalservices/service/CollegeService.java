package com.example.energizeglobalservices.service;

import com.example.energizeglobalservices.model.entity.CollegeEntity;

import java.util.Optional;

public interface CollegeService {
    Optional<CollegeEntity> findCollegeByName(String name);

     CollegeEntity save(CollegeEntity newName);
}
