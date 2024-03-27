package com.example.springprojet.controller;

import com.example.springprojet.model.Message;
import com.example.springprojet.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping
    public String getAllMessages(Model model) {
        List<Message> messages = messageService.getAllMessages();
        model.addAttribute("messages", messages);
        model.addAttribute("message", new Message());
        return "message";
    }

    @PostMapping
    public String addMessage(Message message) {
        messageService.saveMessage(message);
        return "redirect:/messages";
    }
    @GetMapping("/listMessages")
    public String showMessageList(Model model) {
        List<Message> messages = messageService.getAllMessages();
        model.addAttribute("messages", messages);
        return "list1"; // Nom de la vue Thymeleaf pour afficher la liste des messages
    }
    @GetMapping("/message/delete/{id}")
    public String deleteMessage(@PathVariable long id) {
        messageService.removeMessage(id);
        return "redirect:/messages/listMessages";
    }
}