package com.example.springprojet.repository;

import com.example.springprojet.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    List<Job> findByCategoryjobId(Integer id);
}
