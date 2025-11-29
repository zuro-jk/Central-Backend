package com.centrral.centralres.features.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.centrral.centralres.features.products.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.active = true")
    List<Product> findAllActive();

    @Query(value = "SELECT * FROM products", nativeQuery = true)
    List<Product> findAllIncludingInactive();

    @Query(value = "SELECT * FROM products WHERE product_id = :id", nativeQuery = true)
    Optional<Product> findByIdIncludingInactive(Long id);

    @Query("SELECT p FROM Product p WHERE p.active = false")
    List<Product> findAllInactive();

    List<Product> findByCategoryId(Long categoryId);

    Optional<Product> findByName(String name);

    @Query("SELECT p FROM Product p " +
            "LEFT JOIN Review r ON r.product = p " +
            "WHERE p.active = true " +
            "GROUP BY p " +
            "ORDER BY COALESCE(AVG(r.rating), 0) DESC")
    List<Product> findTopRated(Pageable pageable);

}