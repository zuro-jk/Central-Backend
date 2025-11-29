package com.centrral.centralres.features.employees.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centrral.centralres.core.dto.response.PagedResponse;
import com.centrral.centralres.core.security.dto.ApiResponse;
import com.centrral.centralres.features.employees.dto.employee.request.PositionRequest;
import com.centrral.centralres.features.employees.dto.employee.response.PositionResponse;
import com.centrral.centralres.features.employees.service.PositionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<PositionResponse>>> getAll() {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lista de puestos obtenida correctamente", positionService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PositionResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Puesto obtenido correctamente", positionService.getById(id)));
    }

    @GetMapping("/paged")
    public ResponseEntity<ApiResponse<PagedResponse<PositionResponse>>> getAllPaged(Pageable pageable) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Lista paginada de puestos obtenida correctamente",
                        positionService.getAllPaged(pageable)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PositionResponse>> create(@Valid @RequestBody PositionRequest request) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Puesto creado correctamente", positionService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PositionResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody PositionRequest request) {
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Puesto actualizado correctamente", positionService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        positionService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse<>(true, "Puesto eliminado correctamente", null));
    }

}
