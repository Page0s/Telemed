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
import com.telemed.telemed.model.UserType;
import com.telemed.telemed.service.AuthService;
import com.telemed.telemed.service.PatientService;
import com.telemed.telemed.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class DoctorController {

    private final UserService userService;
    private final PatientService patientService;
    private final AuthService authService;

    public DoctorController(UserService userService, PatientService patientService, AuthService authService) {
        this.userService = userService;
        this.patientService = patientService;
        this.authService = authService;
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

    @PostMapping("/createPatient")
    public String createPatient(@RequestParam("name") String name,
                                @RequestParam("surname") String surname,
                                @RequestParam("email") String email,
                                @RequestParam("address") String address,
                                @RequestParam("phone") String phone,
                                @RequestParam("password") String password) {
        UserType userType = new UserType();
        userType.setId(2L);
        AppUser patient = new AppUser(name, surname, email, password, address, phone, userType);
        userService.saveUser(patient);
        return "redirect:/doctorLanding";
    }

    @GetMapping("/initDoctor")
    public String init() {
        if (authService.findByEmail("marko.markic@telemed.hr").isPresent())
            return "login";
        else {
            userService.saveUser(new AppUser("Marko", "Markic", "marko.markic@telemed.hr", "marko123", "Savska 111a", "098123456", new UserType(1L, "Doctor")));
            return "login";
        }
    }
}
