package com.example.dao;

import com.example.model.Patient;
import com.example.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PatientDAO extends PersonDAO {

    private static List<Patient> all_patients = new ArrayList<>();
    
     static {
        all_patients.add(new Patient(1, "Ravindu", "1234567890", "Kalutara", "aaaa", "cccc"));
        all_patients.add(new Patient(2, "Nimal", "0987654321", "Panadura", "bbbb", "ddddd"));
    }
    // Method to get all patients
    public static List<Patient> get_all_patients() {
        return all_patients;
    }
    // Method to add a new patient
    public void add_patient(Patient patient) {
        int newpatientId = get_next_patient_id();
        patient.setID(newpatientId);
        add_Person(patient); // Assuming this method adds patient's personal details
        all_patients.add(patient);
    }
    // Method to update a patient
    public void update_patient(Patient updatedPatient) {
        for (int i = 0; i < all_patients.size(); i++) {
            Patient patient = all_patients.get(i);
            if (patient.getID() == updatedPatient.getID()) {
                all_patients.set(i, updatedPatient);
                System.out.println("Student updated: " + updatedPatient);
                return;
            }
        }
    }
    // Method to delete a patient
    public void delete_patient(int id) throws NotFoundException {
        all_patients.removeIf(patientt -> patientt.getID() == id);
    }
    // Method to get the next available patient ID
     public int get_next_patient_id() {
         
        int maxpatientId = Integer.MIN_VALUE;

        for (Patient patient : all_patients) {
            int userId = patient.getID();
            if (userId > maxpatientId) {
                maxpatientId = userId;
            }
        }

        return maxpatientId + 1;
    }
    // Method to get a patient by ID
      public Patient get_the_patient_id(int id) {
        for (Patient patient : all_patients) {
            if (patient.getID() == id) {
                return patient;
            }
        }
        return null;
    }

}
