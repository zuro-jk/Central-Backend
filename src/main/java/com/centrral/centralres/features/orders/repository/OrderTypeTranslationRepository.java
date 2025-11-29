package com.centrral.centralres.features.orders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.orders.model.OrderType;
import com.centrral.centralres.features.orders.model.OrderTypeTranslation;

public interface OrderTypeTranslationRepository extends JpaRepository<OrderTypeTranslation, Long> {
    Optional<OrderTypeTranslation> findByOrderTypeAndLang(OrderType orderType, String lang);
}