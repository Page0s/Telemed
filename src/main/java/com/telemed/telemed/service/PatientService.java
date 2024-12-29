package com.telemed.telemed.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.telemed.telemed.model.PatientEntry;
import com.telemed.telemed.repository.PatientRepository;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientEntry> getAllPatientEntries() {
        return patientRepository.findAll();
    }

    public void savePatientEntry(PatientEntry patientEntry) {
        patientRepository.save(patientEntry);
    }

    public void deletePatientEntry(int id) {
        patientRepository.deleteById(id);
    }

    public PatientEntry findPatientEntryById(int id) {
        return patientRepository.findById(id);
    }
        
    public void updatePatientEntryById(int id, PatientEntry updatedPatientEntry) {
        patientRepository.updateById(id, updatedPatientEntry);
    }
}
