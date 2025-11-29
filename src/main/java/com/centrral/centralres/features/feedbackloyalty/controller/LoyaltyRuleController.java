package com.centrral.centralres.features.feedbackloyalty.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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
import com.centrral.centralres.features.feedbackloyalty.dto.loyaltyrule.request.LoyaltyRuleRequest;
import com.centrral.centralres.features.feedbackloyalty.dto.loyaltyrule.response.LoyaltyRuleResponse;
import com.centrral.centralres.features.feedbackloyalty.service.LoyaltyRuleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/loyalty-rules")
@RequiredArgsConstructor
public class LoyaltyRuleController {

    private final LoyaltyRuleService loyaltyRuleService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<LoyaltyRuleResponse>>> getAllRules() {
        List<LoyaltyRuleResponse> rules = loyaltyRuleService.getAllRules();
        return ResponseEntity.ok(new ApiResponse<>(true, "Reglas obtenidas correctamente", rules));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<LoyaltyRuleResponse>> getRule(@PathVariable Long id) {
        LoyaltyRuleResponse rule = loyaltyRuleService.getRule(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Regla obtenida correctamente", rule));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LoyaltyRuleResponse>> createRule(
            @Valid @RequestBody LoyaltyRuleRequest request) {
        LoyaltyRuleResponse rule = loyaltyRuleService.createRule(request);
        return new ResponseEntity<>(new ApiResponse<>(true, "Regla creada correctamente", rule), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LoyaltyRuleResponse>> updateRule(
            @PathVariable Long id,
            @Valid @RequestBody LoyaltyRuleRequest request) {
        LoyaltyRuleResponse rule = loyaltyRuleService.updateRule(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Regla actualizada correctamente", rule));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRule(@PathVariable Long id) {
        loyaltyRuleService.deleteRule(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Regla eliminada correctamente", null));
    }
}