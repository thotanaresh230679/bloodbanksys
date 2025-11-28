package com.bloodbank.repository;

import com.bloodbank.entity.BloodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface BloodRequestRepository extends JpaRepository<BloodRequest, Long> {
    List<BloodRequest> findByStatus(String status);
    List<BloodRequest> findByBloodGroup(String bloodGroup);
    
    @Query("SELECT br FROM BloodRequest br WHERE br.urgency = 'HIGH' AND br.status = 'PENDING'")
    List<BloodRequest> findHighPriorityRequests();
    
    List<BloodRequest> findByStatusOrderByRequestDateDesc(String status);
}