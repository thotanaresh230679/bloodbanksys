package com.bloodbank.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "blood_inventory")
public class BloodInventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String bloodGroup;
    
    private int quantity; // in units (1 unit = 450ml)
    
    private LocalDate lastUpdated;
    
    // Default constructor
    public BloodInventory() {
        this.lastUpdated = LocalDate.now();
    }
    
    // Parameterized constructor
    public BloodInventory(String bloodGroup, int quantity) {
        this();
        this.bloodGroup = bloodGroup;
        this.quantity = quantity;
    }
    
    // Parameterized constructor with date
    public BloodInventory(String bloodGroup, int quantity, LocalDate lastUpdated) {
        this.bloodGroup = bloodGroup;
        this.quantity = quantity;
        this.lastUpdated = lastUpdated;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getBloodGroup() {
        return bloodGroup;
    }
    
    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public LocalDate getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    // toString method for debugging
    @Override
    public String toString() {
        return "BloodInventory{" +
                "id=" + id +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", quantity=" + quantity +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}