package com.example.dao;

import com.example.model.Billing;
import com.example.model.Patient;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.NotFoundException;

public class BillingDAO {
    private static List<Billing> billings_count = new ArrayList<>();
    
    static {
        List<Patient> allPatients = PatientDAO.get_all_patients();
        billings_count.add(new Billing(1, allPatients.get(0), 24000, 20000));
        billings_count.add(new Billing(2, allPatients.get(1), 4000, 2000));
    }
    // Method to get all billing information
    public List<Billing> get_all_billings() {
        return billings_count;
    }
    // Method to get billing information by ID
    public Billing get_the_billing(int Id) {
        for (Billing billing : billings_count) {
            if (billing.get_no()== Id) {
                return billing;
            }
        }
        return null;
    }
    // Method to add new billing information
    public void add_the_billing(Billing billing) {
        // Get the next available billing ID
        int newUserId = get_next_bill_ID();
        // Set the ID for the new billing information
        billing.set_no(newUserId);
        // Add the new billing information to the list
        billings_count.add(billing);
    }
    // Method to update billing information
     public void update_the_billing(Billing updatedBilling) {
        for (int i = 0; i < billings_count.size(); i++) {
            Billing the_billing = billings_count.get(i);
            if (the_billing.get_no() == updatedBilling.get_no()) {
                // Update the billing information
                billings_count.set(i, updatedBilling);
                System.out.println("Student updated: " + updatedBilling);
                return;
            }
        }
    }
    // Method to delete billing information by ID
     public void deleting_bills(int No) {
        billings_count.removeIf(Billing -> Billing.get_no() == No);
    }
     // Method to get the next available billing ID
     public int get_next_bill_ID() {
         // Initialize max_user_id with a value lower than any possible user ID
        int max_user_id = Integer.MIN_VALUE;
        // Iterate through the list to find the maximum user ID
        for (Billing i : billings_count) {
            int userId = i.get_no();
            if (userId > max_user_id) {
                max_user_id = userId;
            }
        }
        // Increment the maximum user ID to get the next available user ID
        return max_user_id + 1;
    }

}
