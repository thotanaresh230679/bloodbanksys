package com.bloodbank.service;

import com.bloodbank.entity.BloodRequest;
import com.bloodbank.entity.BloodInventory;
import com.bloodbank.repository.BloodRequestRepository;
import com.bloodbank.repository.BloodInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BloodRequestService {
    
    @Autowired
    private BloodRequestRepository bloodRequestRepository;
    
    @Autowired
    private BloodInventoryRepository bloodInventoryRepository;
    
    public List<BloodRequest> getAllRequests() {
        return bloodRequestRepository.findAll();
    }
    
    public BloodRequest saveRequest(BloodRequest bloodRequest) {
        if (bloodRequest.getRequestDate() == null) {
            bloodRequest.setRequestDate(LocalDate.now());
        }
        return bloodRequestRepository.save(bloodRequest);
    }
    
    public BloodRequest getRequestById(Long id) {
        return bloodRequestRepository.findById(id).orElse(null);
    }
    
    public void deleteRequest(Long id) {
        bloodRequestRepository.deleteById(id);
    }
    
    public List<BloodRequest> getRequestsByStatus(String status) {
        return bloodRequestRepository.findByStatus(status);
    }
    
    public List<BloodRequest> getHighPriorityRequests() {
        return bloodRequestRepository.findHighPriorityRequests();
    }
    
    public BloodRequest updateRequestStatus(Long id, String status) {
        Optional<BloodRequest> requestOpt = bloodRequestRepository.findById(id);
        if (requestOpt.isPresent()) {
            BloodRequest request = requestOpt.get();
            request.setStatus(status);
            
            // If approved, reduce inventory
            if ("APPROVED".equals(status)) {
                updateInventory(request.getBloodGroup(), -request.getUnitsRequired());
            }
            
            return bloodRequestRepository.save(request);
        }
        return null;
    }
    
    private void updateInventory(String bloodGroup, int quantityChange) {
        Optional<BloodInventory> inventoryOpt = bloodInventoryRepository.findByBloodGroup(bloodGroup);
        if (inventoryOpt.isPresent()) {
            BloodInventory inventory = inventoryOpt.get();
            inventory.setQuantity(inventory.getQuantity() + quantityChange);
            inventory.setLastUpdated(LocalDate.now());
            bloodInventoryRepository.save(inventory);
        }
    }
    
    public boolean checkBloodAvailability(String bloodGroup, int unitsRequired) {
        Optional<BloodInventory> inventoryOpt = bloodInventoryRepository.findByBloodGroup(bloodGroup);
        if (inventoryOpt.isPresent()) {
            return inventoryOpt.get().getQuantity() >= unitsRequired;
        }
        return false;
    }
}