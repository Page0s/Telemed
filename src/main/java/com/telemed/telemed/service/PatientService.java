package com.telemed.telemed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.telemed.telemed.model.AppUser;
import com.telemed.telemed.model.PatientRecord;
import com.telemed.telemed.repository.PatientRecordRepository;

@Service
public class PatientService {

    private final PatientRecordRepository patientRecordRepository;

    public PatientService(PatientRecordRepository patientRecordRepository) {
        this.patientRecordRepository = patientRecordRepository;
    }

    public void savePatientRecord(PatientRecord patientRecord) {
        patientRecordRepository.save(patientRecord);
    }

    public List<PatientRecord> getAllPatientRecords(AppUser appUser) {
        return patientRecordRepository.findAllByAppUserId(appUser.getId());
    }

    public Optional<PatientRecord> findPatientRecordById(Long id) {
        return patientRecordRepository.findById(id);
    }
}
