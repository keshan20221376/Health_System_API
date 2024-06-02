package com.example.dao;

import com.example.model.Doctor;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO extends PersonDAO { // Inherits from PersonDAO

    private static List<Doctor> the_doctors = new ArrayList<>();

    static {
        // Static block to initialize sample Doctor objects
        the_doctors.add(new Doctor(1, "Kamal", "0771234567", "Colombo", "Dentist"));
        the_doctors.add(new Doctor(2, "Sunil", "0711234567", "Maharagama", "Neurology"));
    }
    // Method to retrieve all Doctor objects
    public static List<Doctor> get_all_doctors() {
        return the_doctors;
    }
    // Method to get Doctor by ID
    public Doctor get_the_doctor_by_ID(int id) throws NotFoundException {
        for (Doctor doctor : the_doctors) {
            if (doctor.getID() == id) {
                return doctor;
            }
        }
        throw new NotFoundException("Doctor not found! you entered ID: " + id);
    }
    // Method to add a new Doctor
    public void adding_doctor(Doctor doctor) {
        int new_doctor_id = get_next_doctor_id();
        doctor.setID(new_doctor_id);
        add_Person(doctor); // Assuming this method adds doctor's personal details
        the_doctors.add(doctor);
    }
    // Method to delete a Doctor by ID
    public void deleting_doctor(int id) throws NotFoundException {
        the_doctors.removeIf(doctor1 -> doctor1.getID() == id);
        
    }
    // Method to get the next available Doctor ID
     public int get_next_doctor_id() {

        int maxDoctorId = Integer.MIN_VALUE;

        for (Doctor doctor : the_doctors) {
            int userId = doctor.getID();
            if (userId > maxDoctorId) {
                maxDoctorId = userId;
            }
        }
        return maxDoctorId + 1;
    }
    // Method to update a Doctor's details
     public void updating_doctor(Doctor updated_student) {
        for (int i = 0; i < the_doctors.size(); i++) {
            Doctor doctor = the_doctors.get(i);
            if (doctor.getID() == updated_student.getID()) {
                the_doctors.set(i, updated_student);
                System.out.println("The updated student : " + updated_student);
                return;
            }
        }
    }
     
}
