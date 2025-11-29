package com.centrral.centralres.features.employees.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.employees.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
