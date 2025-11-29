package com.centrral.centralres.features.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.centrral.centralres.features.restaurant.model.TableEntity;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
