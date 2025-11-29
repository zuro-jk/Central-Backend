package com.centrral.centralres.features.orders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.orders.model.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    Optional<PaymentMethod> findByCodeAndProvider(String code, String provider);

}
