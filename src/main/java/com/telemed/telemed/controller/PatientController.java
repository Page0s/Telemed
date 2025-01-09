package com.telemed.telemed.controller;

import com.telemed.telemed.model.PatientRecord;
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

    @GetMapping("/addPatientRecord")
    public String addPatientRecord(@RequestParam("heartRate") int heartRate,
                                  @RequestParam("systolic") int systolic,
                                  @RequestParam("diastolic") int diastolic,
                                  @RequestParam("date") String date,
                                  @RequestParam("description") String description) {

        PatientRecord patientRecord = new PatientRecord(heartRate, systolic, diastolic, date, description);
        patientService.savePatientRecord(patientRecord);
        return "redirect:/patientLanding";

    }

    @GetMapping("/patientLanding")
    public String showPatientLanding(Model model) {
        model.addAttribute("patientRecords", patientService.getAllPatientRecords());
        return "patientLanding";
    }

    @GetMapping("/deletePatientRecord")
    public String deletePatientRecord(@RequestParam("id") int id) {
        patientService.deletePatientRecord(id);
        return "redirect:/patientLanding";
    }

    @GetMapping("/editPatientRecord")
    public String editPatientRecord(@RequestParam("id") int id, Model model) {
         model.addAttribute("patientRecord", patientService.findPatientRecordById(id));
        return "editPatientRecord";
    }

    @GetMapping("/updatePatientRecord")
    public String updatePatientRecord(@RequestParam("id") int id,
                                     @RequestParam("heartRate") int heartRate,
                                     @RequestParam("systolic") int systolic,
                                     @RequestParam("diastolic") int diastolic,
                                     @RequestParam("date") String date,
                                     @RequestParam("description") String description) {

        PatientRecord patientRecord = new PatientRecord(heartRate, systolic, diastolic, date, description);
        patientService.updatePatientRecordById(id, patientRecord);

        return "redirect:/patientLanding";
    }

    @GetMapping("/patientRecord")
    public String showPatientRecordForm() {
        return "patientRecord";
    }
}