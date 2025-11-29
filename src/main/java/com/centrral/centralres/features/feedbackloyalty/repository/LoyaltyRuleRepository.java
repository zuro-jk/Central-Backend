package com.centrral.centralres.features.feedbackloyalty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.feedbackloyalty.models.LoyaltyRule;

public interface LoyaltyRuleRepository extends JpaRepository<LoyaltyRule, Long> {
    List<LoyaltyRule> findByActiveTrue();
}
