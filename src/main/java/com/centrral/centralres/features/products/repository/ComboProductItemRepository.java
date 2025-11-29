package com.centrral.centralres.features.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.products.model.ComboProductItem;

public interface ComboProductItemRepository extends JpaRepository<ComboProductItem, Long> {

}
