package com.telemed.telemed.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "patient_record")
public class PatientRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int heartRate;
    private int systolic;
    private int diastolic;
    private Date date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    // Constructors
    public PatientRecord() {}

    public PatientRecord(int heartRate, int systolic, int diastolic, Date date, String description, AppUser appUser) {
        this.heartRate = heartRate;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.date = date;
        this.description = description;
        this.appUser = appUser;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getSystolic() {
        return systolic;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}