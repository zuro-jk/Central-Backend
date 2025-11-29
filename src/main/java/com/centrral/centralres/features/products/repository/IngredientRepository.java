package com.centrral.centralres.features.products.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.products.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    Optional<Ingredient> findByName(String name);
}