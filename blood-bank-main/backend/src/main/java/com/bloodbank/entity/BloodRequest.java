package com.bloodbank.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "blood_requests")
public class BloodRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String patientName;
    
    private String hospitalName;
    
    @Column(nullable = false)
    private String bloodGroup;
    
    private int unitsRequired;
    
    private String urgency; // LOW, MEDIUM, HIGH
    
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED, COMPLETED
    
    private LocalDate requestDate;
    
    private LocalDate requiredDate;
    
    private String contactNumber;
    
    private String reason;
    
    // Constructors
    public BloodRequest() {
        this.requestDate = LocalDate.now();
    }
    
    public BloodRequest(String patientName, String hospitalName, String bloodGroup, 
                       int unitsRequired, String urgency, LocalDate requiredDate) {
        this();
        this.patientName = patientName;
        this.hospitalName = hospitalName;
        this.bloodGroup = bloodGroup;
        this.unitsRequired = unitsRequired;
        this.urgency = urgency;
        this.requiredDate = requiredDate;
    }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    
    public String getHospitalName() { return hospitalName; }
    public void setHospitalName(String hospitalName) { this.hospitalName = hospitalName; }
    
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    
    public int getUnitsRequired() { return unitsRequired; }
    public void setUnitsRequired(int unitsRequired) { this.unitsRequired = unitsRequired; }
    
    public String getUrgency() { return urgency; }
    public void setUrgency(String urgency) { this.urgency = urgency; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDate getRequestDate() { return requestDate; }
    public void setRequestDate(LocalDate requestDate) { this.requestDate = requestDate; }
    
    public LocalDate getRequiredDate() { return requiredDate; }
    public void setRequiredDate(LocalDate requiredDate) { this.requiredDate = requiredDate; }
    
    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}