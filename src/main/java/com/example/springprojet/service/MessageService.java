package com.example.springprojet.service;

import com.example.springprojet.model.Message;
import com.example.springprojet.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
@Autowired
    private MessageRepository messageRepository;
public List<Message> getAllMessages(){
    return messageRepository.findAll();
}
public Message saveMessage(Message message){
    return messageRepository.save(message);
}
    public void removeMessage(Long id){messageRepository.deleteById(id);}
}
