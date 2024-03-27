package com.example.springprojet.model;

import javax.persistence.*;
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    public String description;
    @ManyToOne()
    private Categoryjob categoryjob;

    public Job() {
    }

    public Job(int id, String name, String description, Categoryjob categoryjob) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryjob = categoryjob;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categoryjob getCategoryjob() {
        return categoryjob;
    }

    public void setCategoryjob(Categoryjob categoryjob) {
        this.categoryjob = categoryjob;
    }
}
