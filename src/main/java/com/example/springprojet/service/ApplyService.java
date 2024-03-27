package com.example.springprojet.service;

import com.example.springprojet.model.Apply;
import com.example.springprojet.repository.ApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplyService {
    @Autowired
    ApplyRepository applyRepository;
    public List<Apply> getallappply(){return applyRepository.findAll();}
    public void addapply(Apply apply){
        applyRepository.save(apply);
    }
    public void removeapply(long id){
        applyRepository.deleteById(id);
    }
    public Optional<Apply> getapplytbyid(long id){
        return applyRepository.findById(id);
    }


}
