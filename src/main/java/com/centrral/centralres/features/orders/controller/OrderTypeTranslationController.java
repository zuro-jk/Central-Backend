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
import org.springframework.web.bind.annotation.RestController;

import com.centrral.centralres.core.security.dto.ApiResponse;
import com.centrral.centralres.features.orders.dto.ordertypetranslation.request.OrderTypeTranslationRequest;
import com.centrral.centralres.features.orders.dto.ordertypetranslation.response.OrderTypeTranslationResponse;
import com.centrral.centralres.features.orders.service.OrderTypeTranslationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/order-type-translations")
@RequiredArgsConstructor
public class OrderTypeTranslationController {

    private final OrderTypeTranslationService service;

    @GetMapping("/order-type/{orderTypeId}")
    public ResponseEntity<ApiResponse<List<OrderTypeTranslationResponse>>> getAllByOrderType(
            @PathVariable Long orderTypeId) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Traducciones obtenidas", service.getAllByOrderType(orderTypeId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderTypeTranslationResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Traducción encontrada", service.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<OrderTypeTranslationResponse>> create(
            @Valid @RequestBody OrderTypeTranslationRequest request) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Traducción creada", service.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderTypeTranslationResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody OrderTypeTranslationRequest request) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Traducción actualizada", service.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Traducción eliminada", null));
    }

}
