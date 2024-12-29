package com.telemed.telemed.repository;

import java.util.List;

import com.telemed.telemed.model.PatientEntry;

public interface PatientRepository {

    void save(PatientEntry patient);
    List<PatientEntry> findAll();
    PatientEntry findById(int id);
    void deleteById(int id);
    void updateById(int id, PatientEntry updatedPatientEntry);
}

    /**
     * Optional is a container that may or may not contain a non-null value.
     * It is primarily used for expressing the possibility of a missing value in a way that is most readable.
     * It is not a replacement for using null references, but rather an alternative way of expressing the same
     * concept in a more explicit way.
     * In this method, the method is returning an Optional of PatientEntry, which means that the method may return
     * a PatientEntry object if the patient entry with the given id exists, or an empty Optional if the patient
     * entry does not exist.
     * */
