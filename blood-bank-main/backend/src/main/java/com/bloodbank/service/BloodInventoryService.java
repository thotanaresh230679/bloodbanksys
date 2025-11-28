package com.bloodbank.service;

import com.bloodbank.entity.BloodInventory;
import com.bloodbank.repository.BloodInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BloodInventoryService {
    
    @Autowired
    private BloodInventoryRepository bloodInventoryRepository;
    
    public List<BloodInventory> getAllInventory() {
        return bloodInventoryRepository.findAll();
    }
    
    public BloodInventory saveInventory(BloodInventory inventory) {
        inventory.setLastUpdated(LocalDate.now());
        return bloodInventoryRepository.save(inventory);
    }
    
    public BloodInventory getInventoryByBloodGroup(String bloodGroup) {
        Optional<BloodInventory> inventory = bloodInventoryRepository.findByBloodGroup(bloodGroup);
        return inventory.orElse(null);
    }
    
    public BloodInventory updateInventory(String bloodGroup, int quantityChange) {
        Optional<BloodInventory> inventoryOpt = bloodInventoryRepository.findByBloodGroup(bloodGroup);
        if (inventoryOpt.isPresent()) {
            BloodInventory inventory = inventoryOpt.get();
            inventory.setQuantity(inventory.getQuantity() + quantityChange);
            inventory.setLastUpdated(LocalDate.now());
            return bloodInventoryRepository.save(inventory);
        }
        return null;
    }
}