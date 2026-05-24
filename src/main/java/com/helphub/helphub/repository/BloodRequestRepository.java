package com.helphub.helphub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helphub.helphub.model.BloodRequest;

@Repository
public interface BloodRequestRepository extends JpaRepository<BloodRequest, Long> {
    
    // Find all requests sent by a specific requester
    List<BloodRequest> findByRequesterEmail(String requesterEmail);
    
    // Find all requests received by a specific donor
    List<BloodRequest> findByDonorEmail(String donorEmail);
}
