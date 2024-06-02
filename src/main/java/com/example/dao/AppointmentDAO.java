package com.example.dao;

import com.example.model.Appointment;
import com.example.model.Doctor;
import com.example.model.Patient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;
import jdk.vm.ci.meta.Local;

public class AppointmentDAO {
    private static List<Appointment> pending_appointments = new ArrayList<>();

    
    static {
        LocalDate the_date = LocalDate.now();
        LocalTime the_time = LocalTime.now();

        DateTimeFormatter format_date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter format_time = DateTimeFormatter.ofPattern("HH:mm");

        String formatted_Date = the_date.format(format_date);
        String formatted_Time = the_time.format(format_time);
        
        
        
        List<Doctor> allDoctors = DoctorDAO.get_all_doctors();
        List<Patient> allPatients = PatientDAO.get_all_patients();


        pending_appointments.add(new Appointment(1, formatted_Date, formatted_Time, new Patient(1, "Ravindu", "1234567890", "Kalutara", "aaaa", "cccc"), new Doctor(1, "Kamal", "0771234567", "Colombo", "Dentist")));
        pending_appointments.add(new Appointment(2, formatted_Date, formatted_Time, new Patient(2, "Nimal", "0987654321", "Panadura", "bbbb", "ddddd"), new Doctor(2, "Sunil", "0711234567", "Maharagama", "Neurology")));
        

    }
    // Method to add a new appointment
    public void add_the_Appointment(Appointment appointment) {
        int newUserId = get_Next_AppointmentId();
        appointment.set_id(newUserId);
        pending_appointments.add(appointment);
    }
    // Method to get all pending appointments
    public List<Appointment> trigger_all_patients() {
        return pending_appointments;
    }
    // Method to update appointment by ID and updated appointment object
    public void update_Appointment(int appointmentId, Appointment updatedAppointment) throws NotFoundException {
        Appointment appointment = get_the_AppointmentBy_Id(appointmentId);  
        appointment.set_date(updatedAppointment.get_date());

    }
    // Overloaded method to update appointment directly with the updated appointment object
      public void update_Appointment(Appointment updatedAppointment) {
        for (int i = 0; i < pending_appointments.size(); i++) {
            Appointment appointment = pending_appointments.get(i);
            if (appointment.get_id() == updatedAppointment.get_id()) {
                pending_appointments.set(i, updatedAppointment);
                System.out.println("Student updated: " + updatedAppointment);
                return;
            }
        }
    }
    // Method to get the next available appointment ID
     public int get_Next_AppointmentId() {

        int maxUserId = Integer.MIN_VALUE;

        for (Appointment apointment : pending_appointments) {
            int AppointmentId = apointment.get_id();
            if (AppointmentId > maxUserId) {
                maxUserId = AppointmentId;
            }
        }
        return maxUserId + 1;
    }
    // Method to get an appointment by its ID
     public Appointment get_the_AppointmentBy_Id(int appointmentId) {
        
        for (Appointment appointment : pending_appointments) {
            if (appointment.get_id() == appointmentId) {
                return appointment;
            }
        }
        return null;
    }
     // Method to delete an appointment by its ID 
    public void deleteAppointment(int appointmentId) throws NotFoundException {
        pending_appointments.removeIf(appoin -> appoin.get_id() == appointmentId);
    }
    
    
}
