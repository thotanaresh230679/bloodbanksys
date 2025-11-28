package com.bloodbank.controller;

import com.bloodbank.entity.Donor;
import com.bloodbank.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "http://localhost:3000")
public class DonorController {
    
    @Autowired
    private DonorService donorService;
    
    @GetMapping
    public List<Donor> getAllDonors() {
        return donorService.getAllDonors();
    }
    
    @PostMapping
    public Donor createDonor(@RequestBody Donor donor) {
        return donorService.saveDonor(donor);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Donor> getDonorById(@PathVariable Long id) {
        Donor donor = donorService.getDonorById(id);
        if (donor != null) {
            return ResponseEntity.ok(donor);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody Donor donorDetails) {
        Donor donor = donorService.getDonorById(id);
        if (donor != null) {
            donor.setName(donorDetails.getName());
            donor.setEmail(donorDetails.getEmail());
            donor.setPhone(donorDetails.getPhone());
            donor.setBloodGroup(donorDetails.getBloodGroup());
            donor.setAge(donorDetails.getAge());
            donor.setAddress(donorDetails.getAddress());
            Donor updatedDonor = donorService.saveDonor(donor);
            return ResponseEntity.ok(updatedDonor);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable Long id) {
        donorService.deleteDonor(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/blood-group/{bloodGroup}")
    public List<Donor> getDonorsByBloodGroup(@PathVariable String bloodGroup) {
        return donorService.getDonorsByBloodGroup(bloodGroup);
    }
}