package com.helphub.helphub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.helphub.helphub.model.EmergencyRequest;

public interface EmergencyRequestRepository extends JpaRepository<EmergencyRequest, Long> {

}