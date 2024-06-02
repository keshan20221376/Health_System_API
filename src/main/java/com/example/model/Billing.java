package com.example.model;


public class Billing {
    
    private int InvoiceNo;
    private Patient patient;
    private double payment;
    private double Total;
    private double Balance;
    
    public Billing(){
    
    }
    
    public Billing(int InvoiceNo, Patient patient,double payment,double Total  ){
        this.InvoiceNo = InvoiceNo;
        this.patient = patient;
        this.Total = Total;
        this.payment = payment;
        this.Balance = payment - Total;
    }
    
    public Patient get_patient() {
        return patient;
    }

    public void set_patient(Patient patient) {
        this.patient = patient;
    }
    
    public int get_no() {
        return InvoiceNo;
    }

    public void set_no(int No) {
        this.InvoiceNo = No;
    }
    
    public double getPayment() {
        return payment;
    }

    public void setPayment(double Payment) {
        this.payment = Payment;
    }
    
    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
    
    public double getBalance() {
        return Balance;
    }
    
    public void setBalance(double balance) {
        this.Balance = balance;
    }
}
