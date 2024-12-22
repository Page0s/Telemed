package com.telemed.telemed.controller;

import com.telemed.telemed.model.PatientEntry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {

    private List<PatientEntry> patientEntries;
    private int id = 0;

    public PatientController() {
        this.patientEntries = new ArrayList<>();

        patientEntries.add(new PatientEntry(1,80, 120, 80, "2021-01-01", "Dobro se osjećam"));
        patientEntries.add(new PatientEntry(2,90, 130, 85, "2021-02-01", "nije loše"));
        patientEntries.add(new PatientEntry(3,100, 140, 90, "2021-03-01", "super"));

        id = patientEntries.get(patientEntries.size() - 1).getId();
    }

    @GetMapping("/addPatientEntry")
    public String addPatientEntry(@RequestParam("heartRate") int heartRate,
                                  @RequestParam("systolic") int systolic,
                                  @RequestParam("diastolic") int diastolic,
                                  @RequestParam("date") String date,
                                  @RequestParam("description") String description) {

        PatientEntry patientEntry = new PatientEntry(++id, heartRate, systolic, diastolic, date, description);
        patientEntries.add(patientEntry);
        return "redirect:/patientLanding";
    }

    @GetMapping("/patientEntry")
    public String showPatientEntryForm() {
        return "patientEntry.html";
    }

    @GetMapping("/patientLanding")
    public String showPatientLanding(Model model) {
        model.addAttribute("patientEntries", patientEntries);
        return "patientLanding";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/")
    public String showHome(Model model) {
        model.addAttribute("patientEntries", patientEntries);
        return "index";
    }

    @GetMapping("/deletePatientEntry")
    public String deletePatientEntry(@RequestParam("id") int id) {
        for(PatientEntry patientEntry : patientEntries) {
            if(patientEntry.getId() == id) {
                patientEntries.remove(patientEntry);
                break;
            }
        }
        return "redirect:/patientLanding";
    }
}