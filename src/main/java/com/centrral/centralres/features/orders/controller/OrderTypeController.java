package com.centrral.centralres.features.orders.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.centrral.centralres.core.security.dto.ApiResponse;
import com.centrral.centralres.features.orders.dto.ordertype.request.OrderTypeRequest;
import com.centrral.centralres.features.orders.dto.ordertype.response.OrderTypeResponse;
import com.centrral.centralres.features.orders.service.OrderTypeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-types")
@RequiredArgsConstructor
public class OrderTypeController {

    private final OrderTypeService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderTypeResponse>>> getAll(
            @RequestParam(defaultValue = "es") String lang) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lista de tipos de orden", service.getAll(lang)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderTypeResponse>> getById(
            @PathVariable Long id,
            @RequestParam(defaultValue = "es") String lang) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Tipo de orden encontrado", service.getById(id, lang)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderTypeResponse>> create(
            @Valid @RequestBody OrderTypeRequest request) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Tipo de orden creado", service.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderTypeResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody OrderTypeRequest request) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Tipo de orden actualizado", service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Tipo de orden eliminado", null));
    }

}
