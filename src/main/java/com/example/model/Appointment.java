package com.example.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Appointment {

    private int id; 
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String time;
    
    public Appointment(){
        
    }

    public Appointment(int id, String date, String time, Patient patient, Doctor doctor) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.time = time;
    }

   
    public int get_id() {
        return id;
    }

    public void set_id(int id) {
        this.id = id;
    }

    public Patient get_patient() {
        return patient;
    }

    public void set_patient(Patient patient) {
        this.patient = patient;
    }

    public Doctor get_doctor() {
        return doctor;
    }

    public void set_doctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String get_date() {
        return date;
    }

    public void set_date(String date) {
        this.date = date;
    }
    
     public String get_time() {
        return time;
    }

    public void set_time(String time) {
        this.time = time;
    }
}
