package com.example.springprojet.controller;


import com.example.springprojet.Global.GlobalData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Authentication authentication,Model model) {

        model.addAttribute("cartCount", GlobalData.cart.size());
        if (authentication != null) {
            // Obtenez la liste des rôles de l'utilisateur authentifié
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // Vérifiez les rôles de l'utilisateur et redirigez en conséquence
            if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                return "admin";
            } else if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_FOURNISSEUR"))) {
                return "fournisseur";
            }
        }

        // Si l'utilisateur n'est pas connecté ou ne correspond à aucun rôle, redirigez-le vers la page de connexion
        return "shop";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
