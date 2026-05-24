package com.helphub.helphub.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helphub.helphub.model.Donor;
import com.helphub.helphub.repository.DonorRepository;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin("*")
public class DonorController {

    private final DonorRepository donorRepository;

    public DonorController(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @PostMapping("/add")
    public Donor addDonor(@RequestBody Donor donor) {
        return donorRepository.save(donor);
    }

    @GetMapping("/all")
    public List<Donor> getAllDonors() {
        return donorRepository.findAll();
    }

    @GetMapping("/search")
    public List<Donor> searchDonors(
            @RequestParam String bloodGroup,
            @RequestParam String city) {

        return donorRepository.findByBloodGroupAndCity(bloodGroup, city);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Donor> updateDonor(@PathVariable Long id, @RequestBody Donor donorDetails) {
        return donorRepository.findById(id)
                .map(donor -> {
                    donor.setName(donorDetails.getName());
                    donor.setEmail(donorDetails.getEmail());
                    donor.setPhone(donorDetails.getPhone());
                    donor.setGender(donorDetails.getGender());
                    donor.setAge(donorDetails.getAge());
                    donor.setBloodGroup(donorDetails.getBloodGroup());
                    donor.setCity(donorDetails.getCity());
                    donor.setDisease(donorDetails.getDisease());
                    donor.setAvailability(donorDetails.getAvailability());
                    Donor updatedDonor = donorRepository.save(donor);
                    return ResponseEntity.ok(updatedDonor);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDonor(@PathVariable Long id) {
        return donorRepository.findById(id)
                .map(donor -> {
                    donorRepository.delete(donor);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}