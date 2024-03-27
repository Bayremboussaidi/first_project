package com.example.springprojet.service;


import com.example.springprojet.model.Job;
import com.example.springprojet.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    JobRepository jobRepository;
    public List<Job> getalljob(){return jobRepository.findAll();
    }
    public void addjob(Job job){ jobRepository.save(job);}
    public void removejob(Integer id){jobRepository.deleteById(id);}
    public Optional<Job> jetjobbyid(Integer id){
        return jobRepository.findById(id);
    }

    public List<Job> getallPBC(Integer id){
        return jobRepository.findByCategoryjobId(id);
    }
}
