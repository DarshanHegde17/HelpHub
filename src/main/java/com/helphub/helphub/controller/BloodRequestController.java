package com.helphub.helphub.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helphub.helphub.model.BloodRequest;
import com.helphub.helphub.repository.BloodRequestRepository;

@RestController
@RequestMapping("/api/blood-requests")
@CrossOrigin("*")
public class BloodRequestController {

    private final BloodRequestRepository bloodRequestRepository;

    public BloodRequestController(BloodRequestRepository bloodRequestRepository) {
        this.bloodRequestRepository = bloodRequestRepository;
    }

    @PostMapping("/create")
    public BloodRequest createRequest(@RequestBody BloodRequest request) {
        return bloodRequestRepository.save(request);
    }

    @GetMapping("/all")
    public List<BloodRequest> getAllRequests() {
        return bloodRequestRepository.findAll();
    }

    @GetMapping("/sent/{email}")
    public List<BloodRequest> getRequestsSentByUser(@PathVariable String email) {
        return bloodRequestRepository.findByRequesterEmail(email);
    }

    @GetMapping("/received/{email}")
    public List<BloodRequest> getRequestsReceivedByDonor(@PathVariable String email) {
        return bloodRequestRepository.findByDonorEmail(email);
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<BloodRequest> updateRequestStatus(
            @PathVariable Long id, 
            @RequestBody BloodRequest statusUpdate) {
        return bloodRequestRepository.findById(id)
                .map(request -> {
                    request.setStatus(statusUpdate.getStatus());
                    BloodRequest updated = bloodRequestRepository.save(request);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
