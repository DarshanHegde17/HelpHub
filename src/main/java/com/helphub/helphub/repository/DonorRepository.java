package com.helphub.helphub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helphub.helphub.model.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long> {

    List<Donor> findByBloodGroupAndCity(String bloodGroup, String city);
}