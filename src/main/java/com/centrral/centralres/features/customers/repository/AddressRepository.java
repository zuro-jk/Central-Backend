package com.centrral.centralres.features.customers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centrral.centralres.features.customers.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Page<Address> findByCustomerId(Long customerId, Pageable pageable);
}
