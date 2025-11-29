package com.centrral.centralres.features.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.products.model.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
