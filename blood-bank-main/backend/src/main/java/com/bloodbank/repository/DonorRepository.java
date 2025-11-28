package com.bloodbank.repository;

import com.bloodbank.entity.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    List<Donor> findByBloodGroup(String bloodGroup);
    List<Donor> findByActiveTrue();  // Changed from findByIsActiveTrue
    List<Donor> findByBloodGroupAndActiveTrue(String bloodGroup);  // Changed from findByBloodGroupAndIsActiveTrue
}