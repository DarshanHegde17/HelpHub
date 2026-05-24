package com.helphub.helphub.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

        return emergencyRepository.save(request);
    }

    @GetMapping("/all")
    public List<EmergencyRequest> getAllRequests() {

        return emergencyRepository.findAll();
    }
}