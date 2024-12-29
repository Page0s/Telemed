package com.telemed.telemed.controller;

import com.telemed.telemed.model.PatientEntry;
import com.telemed.telemed.service.PatientService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/addPatientEntry")
    public String addPatientEntry(@RequestParam("heartRate") int heartRate,
                                  @RequestParam("systolic") int systolic,
                                  @RequestParam("diastolic") int diastolic,
                                  @RequestParam("date") String date,
                                  @RequestParam("description") String description) {

        PatientEntry patientEntry = new PatientEntry(heartRate, systolic, diastolic, date, description);
        patientService.savePatientEntry(patientEntry);
        return "redirect:/patientLanding";

    }

    @GetMapping("/patientLanding")
    public String showPatientLanding(Model model) {
        model.addAttribute("patientEntries", patientService.getAllPatientEntries());
        return "patientLanding";
    }

    @GetMapping("/deletePatientEntry")
    public String deletePatientEntry(@RequestParam("id") int id) {
        patientService.deletePatientEntry(id);
        return "redirect:/patientLanding";
    }

    @GetMapping("/editPatientEntry")
    public String editPatientEntry(@RequestParam("id") int id, Model model) {
         model.addAttribute("patientEntry", patientService.findPatientEntryById(id));
        return "editPatientEntry";
    }

    @GetMapping("/updatePatientEntry")
    public String updatePatientEntry(@RequestParam("id") int id,
                                     @RequestParam("heartRate") int heartRate,
                                     @RequestParam("systolic") int systolic,
                                     @RequestParam("diastolic") int diastolic,
                                     @RequestParam("date") String date,
                                     @RequestParam("description") String description) {

        PatientEntry patientEntry = new PatientEntry(heartRate, systolic, diastolic, date, description);
        patientService.updatePatientEntryById(id, patientEntry);

        return "redirect:/patientLanding";
    }

    @GetMapping("/patientEntry")
    public String showPatientEntryForm() {
        return "patientEntry";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
}