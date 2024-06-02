package com.example.dao;

import com.example.model.MedicalRecord;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;
import com.example.model.Patient;

public class MedicalRecordDAO {
    private static List<MedicalRecord> the_medical_records = new ArrayList<>();
    
    static List<Patient> allRecords = PatientDAO.get_all_patients();
    
    static {
        the_medical_records.add(new MedicalRecord(allRecords.get(0).getID(), 1, "aaaa", "cccc", "eeee"));
        the_medical_records.add(new MedicalRecord(allRecords.get(0).getID(), 2, "bbbb", "dddd", "ffff"));
    }
    // Method to retrieve all medical records
      public List<MedicalRecord> get_all_the_medical_records() {
        return the_medical_records;
    }
    // Method to get a medical report by patient ID
    public MedicalRecord get_the_medical_report(int id) {
        int i =0;
        for (MedicalRecord medi_record : the_medical_records) {
            if (medi_record.getPatientId() == id ) {
              
                return medi_record;  
            }
            i++;
        }
        return null;
    }
    // Method to add a new medical record
    public void adding_medical_record(MedicalRecord medical_record) {
        the_medical_records.add(medical_record);
        
    }
    // Method to update a medical record by ID
    public void updating_medical_record(int id, MedicalRecord updatedMedicalRecord) throws NotFoundException {
        MedicalRecord medicalRecord = get_the_medical_report(id);
       
    }
    // Method to delete a medical record by ID
    public void deleting_medical_record(int id) throws NotFoundException {
        MedicalRecord medi_record = get_the_medical_report(id);
        the_medical_records.remove(medi_record);
    }
    // Method to get all medical records for a specific patient
      public List<MedicalRecord> get_medical_recordd(int id) { 
        List<MedicalRecord> same_records = new ArrayList<>();
        for (MedicalRecord medical_record : the_medical_records) {
            if (medical_record.getPatientId() == id) {
                same_records.add(medical_record);
            }
        }
        return same_records;
    }
}
