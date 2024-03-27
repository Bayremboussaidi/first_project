package com.example.springprojet.model;

import javax.persistence.*;
import java.util.List;
@Entity
public class Categoryjob {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany
    private List<Job> jobs;

    public Categoryjob() {
    }

    public Categoryjob(int id, String name, List<Job> jobs) {
        this.id = id;
        this.name = name;
        this.jobs = jobs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
