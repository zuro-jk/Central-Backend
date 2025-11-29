package com.centrral.centralres.features.orders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.orders.model.OrderType;

public interface OrderTypeRepository extends JpaRepository<OrderType, Long> {
    Optional<OrderType> findByCode(String code);
}
