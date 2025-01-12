package com.telemed.telemed.controller;

import com.telemed.telemed.model.AppUser;
import com.telemed.telemed.model.PatientRecord;
import com.telemed.telemed.repository.PatientRecordRepository;

import jakarta.servlet.http.HttpSession;

import java.sql.Date;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {

    private final PatientRecordRepository patientRecordRepository;

    public PatientController(PatientRecordRepository patientRecordRepository) {
        this.patientRecordRepository = patientRecordRepository;
    }

    @GetMapping("/addPatientRecord")
    public String addPatientRecord(@RequestParam("heartRate") int heartRate,
                                  @RequestParam("systolic") int systolic,
                                  @RequestParam("diastolic") int diastolic,
                                  @RequestParam("date") Date date,
                                  @RequestParam("description") String description, HttpSession session) {

        AppUser patient = (AppUser) session.getAttribute("patient");
        PatientRecord patientRecord = new PatientRecord(heartRate, systolic, diastolic, date, description, patient);
        patientRecordRepository.save(patientRecord);
        return "redirect:/patientLanding";
    }

    @GetMapping("/patientLanding")
    public String showPatientLanding(Model model, HttpSession session) {
        AppUser appUser = (AppUser) session.getAttribute("patient");
        model.addAttribute("patient", appUser);
        model.addAttribute("patientRecords", patientRecordRepository.findAllByAppUserId(appUser.getId()));
        return "patientLanding";
    }

    @GetMapping("/deletePatientRecord")
    public String deletePatientRecord(@RequestParam("id") Long id, HttpSession session) {
        patientRecordRepository.deleteById(id);
        return "redirect:/patientLanding";
    }

    @GetMapping("/editPatientRecord")
    public String editPatientRecord(@RequestParam("id") Long id, Model model, HttpSession session) {
        model.addAttribute("patientRecord", patientRecordRepository.findById(id).get());
        return "editPatientRecord";
    }

    @GetMapping("/updatePatientRecord")
    public String updatePatientRecord(@RequestParam("id") Long id,
                                     @RequestParam("heartRate") int heartRate,
                                     @RequestParam("systolic") int systolic,
                                     @RequestParam("diastolic") int diastolic,
                                     @RequestParam("date") Date date,
                                     @RequestParam("description") String description, HttpSession session) {

        Optional<PatientRecord> patientRecord = patientRecordRepository.findById(id);

        if (patientRecord.isPresent()) {
            PatientRecord existingRecord = patientRecord.get();
            existingRecord.setHeartRate(heartRate);
            existingRecord.setSystolic(systolic);
            existingRecord.setDiastolic(diastolic);
            existingRecord.setDate(date);
            existingRecord.setDescription(description);

            patientRecordRepository.save(existingRecord);
        }
        return "redirect:/patientLanding";
    }

    @GetMapping("/patientRecord")
    public String showPatientRecordForm() {
        return "patientRecord";
    }
}