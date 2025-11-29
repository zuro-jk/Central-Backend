package com.centrral.centralres.features.orders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.orders.model.OrderStatus;
import com.centrral.centralres.features.orders.model.OrderStatusTranslation;

public interface OrderStatusTranslationRepository extends JpaRepository<OrderStatusTranslation, Long> {
    Optional<OrderStatusTranslation> findByOrderStatusAndLang(OrderStatus orderStatus, String lang);
}