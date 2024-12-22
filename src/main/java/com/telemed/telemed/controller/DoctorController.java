package com.telemed.telemed.controller;

import com.telemed.telemed.model.Patient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorController {

    private List<Patient> patients;

    public DoctorController() {
        this.patients = new ArrayList<>();

        patients.add(new Patient(1, "Ivo", "Ivić", "ivo.ivić@telemed.hr", "12345"));
        patients.add(new Patient(2, "Marko", "Markić", "marko.markic@telemed.hr", "54321"));
    }

    @GetMapping("/doctorLanding")
    public String showDoctorLanding(Model model) {
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
