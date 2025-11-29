package com.centrral.centralres.features.products.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centrral.centralres.core.security.dto.ApiResponse;
import com.centrral.centralres.features.products.dto.inventorymovement.request.InventoryMovementBatchRequest;
import com.centrral.centralres.features.products.dto.inventorymovement.request.InventoryMovementRequest;
import com.centrral.centralres.features.products.dto.inventorymovement.response.InventoryMovementResponse;
import com.centrral.centralres.features.products.service.InventoryMovementService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/inventory-movements")
@RequiredArgsConstructor
public class InventoryMovementController {

    private final InventoryMovementService movementService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InventoryMovementResponse>>> getAll() {
        List<InventoryMovementResponse> movements = movementService.getAll();
        return ResponseEntity.ok(new ApiResponse<>(true, "Movimientos de inventario obtenidos", movements));
    }

    @GetMapping("/ingredient/{ingredientId}")
    public ResponseEntity<ApiResponse<List<InventoryMovementResponse>>> getByIngredient(
            @PathVariable Long ingredientId) {
        List<InventoryMovementResponse> movements = movementService.getByIngredient(ingredientId);
        return ResponseEntity
                .ok(new ApiResponse<>(true, "Movimientos de inventario por ingrediente obtenidos", movements));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InventoryMovementResponse>> create(
            @Valid @RequestBody InventoryMovementRequest request) {
        InventoryMovementResponse movement = movementService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Movimiento de inventario creado", movement));
    }

    @PostMapping("/batch")
    public ResponseEntity<ApiResponse<List<InventoryMovementResponse>>> createBatch(
            @Valid @RequestBody InventoryMovementBatchRequest batchRequest) {
        List<InventoryMovementResponse> movements = movementService.createBatch(batchRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Movimientos de inventario creados en lote", movements));
    }
}