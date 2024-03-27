package com.example.springprojet.service;

import com.example.springprojet.model.Categoryjob;
import com.example.springprojet.repository.CategoryjobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryjobService {
    @Autowired
    CategoryjobRepository categoryjobRepository;
    public List<Categoryjob> getallcategoryjob(){return  categoryjobRepository.findAll();}
    public void Addcategoryjob(Categoryjob categoryjob){categoryjobRepository.save(categoryjob);}
    public void Removecategoryjob(Integer id){categoryjobRepository.deleteById(id);}
    public Optional<Categoryjob> getcategoryjobbyid(Integer id){
        return  categoryjobRepository.findById(id);
    }

}
