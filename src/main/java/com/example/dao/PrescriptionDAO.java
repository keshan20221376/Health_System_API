package com.example.dao;
import com.example.model.Doctor;
import com.example.model.Patient;
import com.example.model.Prescription;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class PrescriptionDAO {
    private static List<Prescription> all_prescriptions = new ArrayList<>();
    
    static {
        // Get all patients and doctors
        List<Patient> allPatients = PatientDAO.get_all_patients();
        List<Doctor> allDoctors = DoctorDAO.get_all_doctors();
        
        // Add sample prescriptions
        all_prescriptions.add(new Prescription(allPatients.get(0), allDoctors.get(0),"aaaa", 4, "cccc", 4));
        all_prescriptions.add(new Prescription(allPatients.get(1), allDoctors.get(0),"bbbb", 5, "dddd", 5));
    }
    // Method to get all prescriptions
    public List<Prescription> get_all_prescriptions() {
        return all_prescriptions;
        
    }
    // Method to update a prescription
    public void update_prescription(Prescription updatedPrescription) {
        for (int i = 0; i < all_prescriptions.size(); i++) {
            Prescription prescription = all_prescriptions.get(i);
            if (prescription.getPatient().getID() == updatedPrescription.getPatient().getID()) {
                all_prescriptions.set(i, updatedPrescription);
                System.out.println("updated student : " + updatedPrescription);
                return;
            }
        }
    }
    
    
    // Method to delete a prescription by patient ID
    public void delete_prescription(int id) throws NotFoundException {
        Prescription the_prescription = get_prescription(id);
        all_prescriptions.remove(the_prescription);
    }
    // Method to get a prescription by patient ID
    public Prescription get_prescription(int id) throws NotFoundException {
        for (Prescription prescription : all_prescriptions) {
            if (prescription.getPatient().getID() == id) {
                return prescription;
            }
        }
        // Throw NotFoundException if prescription for given patient ID is not found
        return null;
    }

    // Method to add a prescription
    public void add_prescription(Prescription the_prescription) {
        all_prescriptions.add(the_prescription);
    }


}