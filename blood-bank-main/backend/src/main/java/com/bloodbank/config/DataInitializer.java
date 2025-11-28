package com.bloodbank.config;

import com.bloodbank.entity.*;
import com.bloodbank.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DonorRepository donorRepository;
    
    @Autowired
    private BloodInventoryRepository bloodInventoryRepository;
    
    @Autowired
    private BloodRequestRepository bloodRequestRepository;

    @Override
    public void run(String... args) throws Exception {
        // Add sample donors
        if (donorRepository.count() == 0) {
            addSampleDonors();
        }

        // Add sample blood inventory
        if (bloodInventoryRepository.count() == 0) {
            addSampleInventory();
        }
        
        // Add sample blood requests
        if (bloodRequestRepository.count() == 0) {
            addSampleRequests();
        }
    }
    
    private void addSampleDonors() {
        Donor donor1 = new Donor("John Smith", "john.smith@email.com", "123-456-7890", 
                                "A+", 28, "123 Main Street, City");
        donor1.setLastDonationDate(LocalDate.now().minusMonths(3));
        donor1.setActive(true);  // Use setActive instead of setIsActive
        donorRepository.save(donor1);
    }
    
    private void addSampleInventory() {
        String[] bloodGroups = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        int[] quantities = {25, 8, 18, 5, 12, 3, 30, 6};
        
        for (int i = 0; i < bloodGroups.length; i++) {
            BloodInventory inventory = new BloodInventory();
            inventory.setBloodGroup(bloodGroups[i]);
            inventory.setQuantity(quantities[i]);
            inventory.setLastUpdated(LocalDate.now());
            bloodInventoryRepository.save(inventory);
        }
        
        System.out.println("Blood inventory initialized!");
    }
    
    private void addSampleRequests() {
        BloodRequest request1 = new BloodRequest("Michael Brown", "City General Hospital", 
                                               "A+", 3, "HIGH", LocalDate.now().plusDays(1));
        request1.setContactNumber("111-222-3333");
        request1.setReason("Emergency surgery");
        bloodRequestRepository.save(request1);
        
        BloodRequest request2 = new BloodRequest("Emily Davis", "Community Medical Center", 
                                               "O-", 2, "MEDIUM", LocalDate.now().plusDays(3));
        request2.setContactNumber("444-555-6666");
        request2.setReason("Regular transfusion");
        bloodRequestRepository.save(request2);
        
        BloodRequest request3 = new BloodRequest("James Wilson", "University Hospital", 
                                               "B+", 4, "LOW", LocalDate.now().plusDays(7));
        request3.setContactNumber("777-888-9999");
        request3.setReason("Scheduled operation");
        bloodRequestRepository.save(request3);
        
        System.out.println("Sample blood requests added to database!");
    }
}