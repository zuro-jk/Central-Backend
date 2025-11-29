package com.centrral.centralres.features.orders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.orders.model.PaymentMethod;
import com.centrral.centralres.features.orders.model.PaymentMethodTranslation;

public interface PaymentMethodTranslationRepository extends JpaRepository<PaymentMethodTranslation, Long> {
    Optional<PaymentMethodTranslation> findByPaymentMethodAndLang(PaymentMethod paymentMethod, String lang);
}
