package com.bloodbank.controller;

import com.bloodbank.entity.BloodInventory;
import com.bloodbank.service.BloodInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "http://localhost:3000")
public class BloodInventoryController {
    
    @Autowired
    private BloodInventoryService bloodInventoryService;
    
    @GetMapping
    public List<BloodInventory> getAllInventory() {
        return bloodInventoryService.getAllInventory();
    }
    
    @PostMapping
    public BloodInventory createInventory(@RequestBody BloodInventory inventory) {
        return bloodInventoryService.saveInventory(inventory);
    }
    
    @GetMapping("/{bloodGroup}")
    public ResponseEntity<BloodInventory> getInventoryByBloodGroup(@PathVariable String bloodGroup) {
        BloodInventory inventory = bloodInventoryService.getInventoryByBloodGroup(bloodGroup);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{bloodGroup}")
    public ResponseEntity<BloodInventory> updateInventory(@PathVariable String bloodGroup, @RequestParam int quantityChange) {
        BloodInventory inventory = bloodInventoryService.updateInventory(bloodGroup, quantityChange);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        }
        return ResponseEntity.notFound().build();
    }
}