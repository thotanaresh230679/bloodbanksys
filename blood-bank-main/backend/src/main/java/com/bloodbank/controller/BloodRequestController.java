package com.bloodbank.controller;

import com.bloodbank.entity.BloodRequest;
import com.bloodbank.service.BloodRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/requests")
@CrossOrigin(origins = "http://localhost:3000")
public class BloodRequestController {
    
    @Autowired
    private BloodRequestService bloodRequestService;
    
    @GetMapping
    public List<BloodRequest> getAllRequests() {
        return bloodRequestService.getAllRequests();
    }
    
    @PostMapping
    public BloodRequest createRequest(@RequestBody BloodRequest bloodRequest) {
        return bloodRequestService.saveRequest(bloodRequest);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BloodRequest> getRequestById(@PathVariable Long id) {
        BloodRequest request = bloodRequestService.getRequestById(id);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<BloodRequest> updateRequest(@PathVariable Long id, @RequestBody BloodRequest requestDetails) {
        BloodRequest request = bloodRequestService.getRequestById(id);
        if (request != null) {
            request.setPatientName(requestDetails.getPatientName());
            request.setHospitalName(requestDetails.getHospitalName());
            request.setBloodGroup(requestDetails.getBloodGroup());
            request.setUnitsRequired(requestDetails.getUnitsRequired());
            request.setUrgency(requestDetails.getUrgency());
            request.setRequiredDate(requestDetails.getRequiredDate());
            request.setContactNumber(requestDetails.getContactNumber());
            request.setReason(requestDetails.getReason());
            
            BloodRequest updatedRequest = bloodRequestService.saveRequest(request);
            return ResponseEntity.ok(updatedRequest);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        bloodRequestService.deleteRequest(id);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/status/{status}")
    public List<BloodRequest> getRequestsByStatus(@PathVariable String status) {
        return bloodRequestService.getRequestsByStatus(status);
    }
    
    @GetMapping("/high-priority")
    public List<BloodRequest> getHighPriorityRequests() {
        return bloodRequestService.getHighPriorityRequests();
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<BloodRequest> updateRequestStatus(@PathVariable Long id, @RequestParam String status) {
        BloodRequest request = bloodRequestService.updateRequestStatus(id, status);
        if (request != null) {
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }
    
    @GetMapping("/check-availability")
    public ResponseEntity<Boolean> checkBloodAvailability(@RequestParam String bloodGroup, @RequestParam int units) {
        boolean available = bloodRequestService.checkBloodAvailability(bloodGroup, units);
        return ResponseEntity.ok(available);
    }
}