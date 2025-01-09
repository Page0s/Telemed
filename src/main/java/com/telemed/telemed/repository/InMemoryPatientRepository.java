package com.telemed.telemed.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.telemed.telemed.model.PatientRecord;

@Repository
public class InMemoryPatientRepository implements PatientRepositoryInMem {

    private List<PatientRecord> patientRecords = new ArrayList<>();
    
        @Override
        public void save(PatientRecord patientRecord) {
            patientRecords.add(patientRecord);
        }

    @Override
    public List<PatientRecord> findAll() {
        return patientRecords;
    }

    @Override
    public PatientRecord findById(int id) {
    
        PatientRecord patientRecord = null;
        for (PatientRecord record : patientRecords) {
            if (record.getId() == id) {
                patientRecord = record;
                break;
            }
        }
        return patientRecord;
    }

    @Override
    public void deleteById(int id) {
        patientRecords.removeIf(patientRecord -> patientRecord.getId() == id);
    }

    @Override
    public void updateById(int id, PatientRecord updatedPatientRecord) {
        
        for (PatientRecord patientRecord : patientRecords) {
            if (patientRecord.getId() == id) {
                patientRecord.setHeartRate(updatedPatientRecord.getHeartRate());
                patientRecord.setSystolic(updatedPatientRecord.getSystolic());
                patientRecord.setDiastolic(updatedPatientRecord.getDiastolic());
                patientRecord.setDate(updatedPatientRecord.getDate());
                patientRecord.setDescription(updatedPatientRecord.getDescription());
                break;
            }
        }
    }
}
