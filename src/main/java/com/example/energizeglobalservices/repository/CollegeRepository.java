package com.example.energizeglobalservices.repository;

import com.example.energizeglobalservices.model.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollegeRepository extends JpaRepository<CollegeEntity, Long> {

    Optional<CollegeEntity> findByName(String name);

}
