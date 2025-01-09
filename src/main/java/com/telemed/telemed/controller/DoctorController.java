package com.telemed.telemed.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.telemed.telemed.model.AppUser;
import com.telemed.telemed.model.PatientRecord;
import com.telemed.telemed.service.PatientService;
import com.telemed.telemed.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DoctorController {

    private final UserService userService;
    private final PatientService patientService;

    public DoctorController(UserService userService, PatientService patientService) {
        this.userService = userService;
        this.patientService = patientService;
    }

    @GetMapping("/doctorLanding")
    public String showDoctorLanding(Model model, HttpSession session) {

        AppUser doctor = (AppUser) session.getAttribute("doctor");
        model.addAttribute("doctor", doctor);

        List<AppUser> patients = userService.getAllPatients(2L);
        model.addAttribute("patients", patients);
        return "doctorLanding";
    }

    @GetMapping("/patientOverview/{id}")
    public String showPatientOverview(@PathVariable Long id, Model model) {
        AppUser patient = userService.getUserById(id).get();
        List<PatientRecord> patientRecords = patientService.getAllPatientRecords(patient);
        model.addAttribute("patient", patient);
        model.addAttribute("patientRecords", patientRecords);
        return "patientOverview";
    }

    @GetMapping("/createPatient")
    public String showCreatePatient() {
        return "createPatient";
    }
}
