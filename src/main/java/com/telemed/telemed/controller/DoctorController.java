package com.telemed.telemed.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.telemed.telemed.model.AppUser;
import com.telemed.telemed.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DoctorController {

    private final UserService userService;

    public DoctorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/doctorLanding")
    public String showDoctorLanding(Model model, HttpSession session) {

        AppUser doctor = (AppUser) session.getAttribute("doctor");
        model.addAttribute("doctor", doctor);

        List<AppUser> patients = userService.getAllPatients(2L);
        model.addAttribute("patients", patients);
        return "doctorLanding";
    }

    @GetMapping("/patientOverview")
    public String showPatientOverview() {
        return "patientOverview";
    }

    @GetMapping("/createPatient")
    public String showCreatePatient() {
        return "createPatient";
    }
}
