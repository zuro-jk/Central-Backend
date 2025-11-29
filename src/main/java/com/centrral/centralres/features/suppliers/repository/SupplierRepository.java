package com.centrral.centralres.features.suppliers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centrral.centralres.features.suppliers.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByCompanyName(String companyName);
}
