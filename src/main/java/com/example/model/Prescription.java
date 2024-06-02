package com.example.model;


public class Prescription {
    
    private Patient patient;
    private Doctor doctor;
    private String medication;
    private int dosage;
    private String instructions;
    private int durationInDays;
    
    public Prescription(){
    
    }

    public Prescription(Patient patient, Doctor doctor, String medication, int dosage, String instructions, int durationInDays) {
        this.patient = patient;
        this.doctor = doctor;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.durationInDays = durationInDays;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getDurationInDays() {
        return durationInDays;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }
}
