package com.telemed.telemed.controller;

import com.telemed.telemed.model.AppUser;
import com.telemed.telemed.model.PatientRecord;
import com.telemed.telemed.service.PatientService;

import jakarta.servlet.http.HttpSession;

import java.sql.Date;
import java.util.Optional;

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

    @GetMapping("/addPatientRecord")
    public String addPatientRecord(@RequestParam("heartRate") int heartRate,
                                  @RequestParam("systolic") int systolic,
                                  @RequestParam("diastolic") int diastolic,
                                  @RequestParam("date") Date date,
                                  @RequestParam("description") String description, HttpSession session) {

        AppUser patient = (AppUser) session.getAttribute("patient");
        PatientRecord patientRecord = new PatientRecord(heartRate, systolic, diastolic, date, description, patient);
        patientService.savePatientRecord(patientRecord);
        return "redirect:/patientLanding";

    }

    @GetMapping("/patientLanding")
    public String showPatientLanding(Model model, HttpSession session) {
        AppUser patient = (AppUser) session.getAttribute("patient");
        model.addAttribute("patient", patient);
        model.addAttribute("patientRecords", patientService.getAllPatientRecords(patient));
        return "patientLanding";
    }

    @GetMapping("/deletePatientRecord")
    public String deletePatientRecord(@RequestParam("id") Long id) {
        patientService.deletePatientRecord(id);
        return "redirect:/patientLanding";
    }

    @GetMapping("/editPatientRecord")
    public String editPatientRecord(@RequestParam("id") Long id, Model model) {
        model.addAttribute("patientRecord", patientService.findPatientRecordById(id).get());
        return "editPatientRecord";
    }

    @GetMapping("/updatePatientRecord")
    public String updatePatientRecord(@RequestParam("id") Long id,
                                     @RequestParam("heartRate") int heartRate,
                                     @RequestParam("systolic") int systolic,
                                     @RequestParam("diastolic") int diastolic,
                                     @RequestParam("date") Date date,
                                     @RequestParam("description") String description) {

        Optional<PatientRecord> patientRecord = patientService.findPatientRecordById(id);

        if (patientRecord.isPresent()) {
            PatientRecord existingRecord = patientRecord.get();
            existingRecord.setHeartRate(heartRate);
            existingRecord.setSystolic(systolic);
            existingRecord.setDiastolic(diastolic);
            existingRecord.setDate(date);
            existingRecord.setDescription(description);

            patientService.savePatientRecord(existingRecord);
        }
        return "redirect:/patientLanding";
    }

    @GetMapping("/patientRecord")
    public String showPatientRecordForm() {
        return "patientRecord";
    }
}