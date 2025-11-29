package com.centrral.centralres.features.feedbackloyalty.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.feedbackloyalty.models.Reward;

public interface RewardRepository extends JpaRepository<Reward, Long> {
    List<Reward> findByActiveTrue();
}
