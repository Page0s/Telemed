package com.telemed.telemed.controller;

import com.telemed.telemed.model.PatientEntry;
import com.telemed.telemed.repository.PatientRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/addPatientEntry")
    public String addPatientEntry(@RequestParam("heartRate") int heartRate,
                                  @RequestParam("systolic") int systolic,
                                  @RequestParam("diastolic") int diastolic,
                                  @RequestParam("date") String date,
                                  @RequestParam("description") String description) {

        PatientEntry patientEntry = new PatientEntry(heartRate, systolic, diastolic, date, description);
        patientRepository.save(patientEntry);
        return "redirect:/patientLanding";

    }
    @GetMapping("/patientLanding")
    public String showPatientLanding(Model model) {
        model.addAttribute("patientEntries", patientRepository.findAll());
        return "patientLanding";
    }

    @GetMapping("/deletePatientEntry")
    public String deletePatientEntry(@RequestParam("id") int id) {
        patientRepository.deleteById(id);
        return "redirect:/patientLanding";
    }

    @GetMapping("/patientEntry")
    public String showPatientEntryForm() {
        return "patientEntry.html";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
}