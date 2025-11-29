package com.centrral.centralres.features.products.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centrral.centralres.features.products.dto.unit.request.UnitRequest;
import com.centrral.centralres.features.products.dto.unit.response.UnitResponse;
import com.centrral.centralres.features.products.service.UnitService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/units")
@RequiredArgsConstructor
public class UnitController {
    private final UnitService unitService;

    @PostMapping
    public ResponseEntity<UnitResponse> create(@Valid @RequestBody UnitRequest request) {
        return ResponseEntity.ok(unitService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<UnitResponse>> findAll() {
        return ResponseEntity.ok(unitService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnitResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(unitService.getById(id));
    }
}
