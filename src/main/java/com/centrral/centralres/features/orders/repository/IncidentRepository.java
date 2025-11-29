package com.centrral.centralres.features.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.orders.model.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
