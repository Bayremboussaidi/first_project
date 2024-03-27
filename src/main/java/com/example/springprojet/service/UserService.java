package com.example.springprojet.service;

import com.example.springprojet.model.User;
import com.example.springprojet.web.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
}
