package com.telemed.telemed.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.telemed.telemed.model.AppUser;
import com.telemed.telemed.service.AuthService;

import jakarta.servlet.http.HttpSession;

@Controller
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        
        Optional<AppUser> user = authService.findByEmail(email);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            if (user.get().getId() == 1) {
                session.setAttribute("doctor", user.get());
                return "redirect:/doctorLanding";
            } else {
                session.setAttribute("patient", user.get());
                return "redirect:/patientLanding";
            }
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
