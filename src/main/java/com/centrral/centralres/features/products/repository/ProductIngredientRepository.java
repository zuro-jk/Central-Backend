package com.centrral.centralres.features.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.products.model.ProductIngredient;

public interface ProductIngredientRepository extends JpaRepository<ProductIngredient, Long> {
}
