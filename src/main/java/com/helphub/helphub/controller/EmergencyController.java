package com.helphub.helphub.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helphub.helphub.model.EmergencyRequest;
import com.helphub.helphub.repository.EmergencyRequestRepository;

@RestController
@RequestMapping("/api/emergency")
@CrossOrigin("*")
public class EmergencyController {

    private final EmergencyRequestRepository emergencyRepository;

    public EmergencyController(EmergencyRequestRepository emergencyRepository) {
        this.emergencyRepository = emergencyRepository;
    }

    @PostMapping("/create")
    public EmergencyRequest createRequest(@RequestBody EmergencyRequest request) {
        // Set expiry date (7 days from creation)
        if (request.getExpiresAt() == null) {
            request.setExpiresAt(LocalDateTime.now().plusDays(7));
        }
        return emergencyRepository.save(request);
    }

    @GetMapping("/all")
    public List<EmergencyRequest> getAllRequests() {
        // Delete expired requests
        List<EmergencyRequest> allRequests = emergencyRepository.findAll();
        LocalDateTime now = LocalDateTime.now();
        allRequests.removeIf(request -> {
            if (request.getExpiresAt() != null && request.getExpiresAt().isBefore(now)) {
                emergencyRepository.delete(request);
                return true;
            }
            return false;
        });
        return allRequests;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmergencyRequest> updateRequest(
            @PathVariable Long id, 
            @RequestBody EmergencyRequest requestDetails) {
        return emergencyRepository.findById(id)
                .map(request -> {
                    request.setName(requestDetails.getName());
                    request.setEmail(requestDetails.getEmail());
                    request.setPhone(requestDetails.getPhone());
                    request.setRequestType(requestDetails.getRequestType());
                    request.setDescription(requestDetails.getDescription());
                    request.setLocation(requestDetails.getLocation());
                    request.setStatus(requestDetails.getStatus());
                    if (requestDetails.getExpiresAt() != null) {
                        request.setExpiresAt(requestDetails.getExpiresAt());
                    }
                    EmergencyRequest updated = emergencyRepository.save(request);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long id) {
        return emergencyRepository.findById(id)
                .map(request -> {
                    emergencyRepository.delete(request);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}