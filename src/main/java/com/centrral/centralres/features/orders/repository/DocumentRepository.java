package com.centrral.centralres.features.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centrral.centralres.features.orders.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
