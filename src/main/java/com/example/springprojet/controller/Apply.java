package com.example.springprojet.controller;

import com.example.springprojet.service.ApplyService;
import com.example.springprojet.service.EmailSenderService;
import com.example.springprojet.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class Apply{
    @Autowired
    private ApplyService applyService;
    @Autowired
    JobService jobService;
    @Autowired
    private EmailSenderService senderService;

    @GetMapping("/formjob")
    public String showApplyForm(Model model) {
        model.addAttribute("apply", new com.example.springprojet.model.Apply());
        return "apply";
    }

    @PostMapping("/apply/save")
    public String saveApply(@ModelAttribute("apply") com.example.springprojet.model.Apply apply) { // Utilisation de la classe Apply du package model
        applyService.addapply(apply);
        return "apply";
    }

    @GetMapping("/list")
    public String showApplyList(Model model) {
        List<com.example.springprojet.model.Apply> applies = applyService.getallappply(); // Utilisation de la classe Apply du package model
        model.addAttribute("applies", applies);
        return "apply-list";
    }
    @GetMapping("/admin/apply/delete/{id}")
    public String deleteapply(@PathVariable int id) {
        applyService.removeapply(id);
        return "redirect:/list";
    }

    @GetMapping("/admin/apply/update/{id}")
    public String updatejob(@PathVariable int id, Model model) {
        com.example.springprojet.model.Apply apply=applyService.getapplytbyid(id).get();
        apply.setId(apply.getId());
        apply.setName(apply.getName());
        apply.setAccepted(apply.isAccepted());
        apply.setCv(apply.getCv());
        apply.setEmail(apply.getEmail());
        model.addAttribute("apply", apply);
        return "apply";
    }
    @GetMapping("/sendEmail/{id}")
    public String sendEmail(@PathVariable int id,Model model){
        com.example.springprojet.model.Apply apply=applyService.getapplytbyid(id).get();
        model.addAttribute("applies", apply);
        senderService.sendEmail(apply.getEmail(),"Demande de travaille ",
                "vous etes accepter");
        return "sendEmail";
    }






}