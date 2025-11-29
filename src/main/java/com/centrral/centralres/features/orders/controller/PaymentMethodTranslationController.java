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
import com.centrral.centralres.features.orders.dto.paymentmethodtranslation.request.PaymentMethodTranslationRequest;
import com.centrral.centralres.features.orders.dto.paymentmethodtranslation.response.PaymentMethodTranslationResponse;
import com.centrral.centralres.features.orders.service.PaymentMethodTranslationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/payment-method-translations")
@RequiredArgsConstructor
public class PaymentMethodTranslationController {

    private final PaymentMethodTranslationService translationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentMethodTranslationResponse>>> getAll() {
        return ResponseEntity
                .ok(new ApiResponse<>(true, "Lista de traducciones de métodos de pago", translationService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentMethodTranslationResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Traducción encontrada", translationService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentMethodTranslationResponse>> create(
            @Valid @RequestBody PaymentMethodTranslationRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Traducción creada", translationService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentMethodTranslationResponse>> update(@PathVariable Long id,
            @Valid @RequestBody PaymentMethodTranslationRequest request) {
        return ResponseEntity
                .ok(new ApiResponse<>(true, "Traducción actualizada", translationService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        translationService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Traducción eliminada", null));
    }
}
