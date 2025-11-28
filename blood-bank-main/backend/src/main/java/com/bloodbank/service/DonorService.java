package com.bloodbank.service;

import com.bloodbank.entity.Donor;
import com.bloodbank.repository.DonorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DonorService {
    
    @Autowired
    private DonorRepository donorRepository;
    
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }
    
    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }
    
    public Donor getDonorById(Long id) {
        return donorRepository.findById(id).orElse(null);
    }
    
    public void deleteDonor(Long id) {
        donorRepository.deleteById(id);
    }
    
    public List<Donor> getDonorsByBloodGroup(String bloodGroup) {
        return donorRepository.findByBloodGroupAndActiveTrue(bloodGroup); 
    }
}