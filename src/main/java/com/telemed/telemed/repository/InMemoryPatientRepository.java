package com.telemed.telemed.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.telemed.telemed.model.PatientEntry;

@Repository
public class InMemoryPatientRepository implements PatientRepository {

    private List<PatientEntry> patientEntries = new ArrayList<>();
    
        @Override
        public void save(PatientEntry patientEntry) {
            patientEntries.add(patientEntry);
        }

    @Override
    public List<PatientEntry> findAll() {
        return patientEntries;
    }

    @Override
    public Optional<PatientEntry> findById(int id) {
        return patientEntries.stream()
                .filter(patientEntry -> patientEntry.getId() == id)
                .findFirst();
    }

    @Override
    public void deleteById(int id) {
        patientEntries.removeIf(patientEntry -> patientEntry.getId() == id);
    }
}
