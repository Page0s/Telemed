package com.telemed.telemed.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.telemed.telemed.model.PatientRecord;
import com.telemed.telemed.repository.PatientRepositoryInMem;

@Service
public class PatientServiceInMem {

    private final PatientRepositoryInMem patientRepository;

    public PatientServiceInMem(PatientRepositoryInMem patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientRecord> getAllPatientRecords() {
        return patientRepository.findAll();
    }

    public void savePatientRecord(PatientRecord patientRecord) {
        patientRepository.save(patientRecord);
    }

    public void deletePatientRecord(int id) {
        patientRepository.deleteById(id);
    }

    public PatientRecord findPatientRecordById(int id) {
        return patientRepository.findById(id);
    }
        
    public void updatePatientRecordById(int id, PatientRecord updatedPatientRecord) {
        patientRepository.updateById(id, updatedPatientRecord);
    }
}
