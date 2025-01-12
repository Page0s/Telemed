package com.telemed.telemed.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.telemed.telemed.model.AppUser;
import com.telemed.telemed.model.PatientRecord;
import com.telemed.telemed.repository.AppUserRepository;
import com.telemed.telemed.repository.PatientRecordRepository;

import jakarta.servlet.http.HttpSession;


@Controller
public class DoctorController {

    private final AppUserRepository appUserRepository;
    private final PatientRecordRepository patientRecordRepository;

    public DoctorController(AppUserRepository appUserRepository, PatientRecordRepository patientRecordRepository) {
        this.appUserRepository = appUserRepository;
        this.patientRecordRepository = patientRecordRepository;
    }

    @GetMapping("/doctorLanding")
    public String showDoctorLanding(Model model, HttpSession session) {

        AppUser doctor = (AppUser) session.getAttribute("doctor");
        model.addAttribute("doctor", doctor);

        List<AppUser> patients = appUserRepository.findAllByUserType(2);
        model.addAttribute("patients", patients);
        return "doctorLanding";
    }

    @GetMapping("/patientOverview/{id}")
    public String showPatientOverview(@PathVariable Long id, Model model) {
        AppUser appUser = appUserRepository.findById(id).get();
        List<PatientRecord> patientRecords = patientRecordRepository.findAllByAppUserId(appUser.getId());
        model.addAttribute("patient", appUser);
        model.addAttribute("patientRecords", patientRecords);
        return "patientOverview";
    }

    @GetMapping("/createPatient")
    public String showCreatePatient() {
        return "createPatient";
    }

    @PostMapping("/createPatient")
    public String createPatient(@RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("email") String email,
                                @RequestParam("address") String address,
                                @RequestParam("phone") String phone,
                                @RequestParam("password") String password) {
        AppUser patient = new AppUser(name, surname, email, password, address, phone, 2);
        appUserRepository.save(patient);
        return "redirect:/doctorLanding";
    }

    @GetMapping("/initDoctor")
    public String init() {
        if (appUserRepository.findByEmail("marko.markic@telemed.hr").isPresent())
            return "login";
        else {
            appUserRepository.save(new AppUser("Marko", "Markic", "marko.markic@telemed.hr", "marko123", "Savska 111a", "098123456", 1));
            return "login";
        }
    }
}
