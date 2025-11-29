package com.centrral.centralres.features.products.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.products.model.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    Optional<Unit> findBySymbol(String symbol);
}
