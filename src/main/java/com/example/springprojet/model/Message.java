package com.example.springprojet.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
    private LocalDateTime timesteamp=LocalDateTime.now();
    public Message(){

    }
    public Message(String content,User user){
        this.content=content;
        this.user=user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimesteamp() {
        return timesteamp;
    }

    public void setTimesteamp(LocalDateTime timesteamp) {
        this.timesteamp = timesteamp;
    }
}
