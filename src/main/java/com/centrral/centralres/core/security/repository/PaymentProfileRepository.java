package com.centrral.centralres.core.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centrral.centralres.core.security.model.PaymentProfile;

@Repository
public interface PaymentProfileRepository extends JpaRepository<PaymentProfile, Long> {
}
