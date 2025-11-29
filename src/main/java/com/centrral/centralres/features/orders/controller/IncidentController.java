package com.centrral.centralres.features.orders.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centrral.centralres.core.security.dto.ApiResponse;
import com.centrral.centralres.features.orders.dto.incident.request.IncidentRequest;
import com.centrral.centralres.features.orders.dto.incident.response.IncidentResponse;
import com.centrral.centralres.features.orders.service.IncidentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<IncidentResponse>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Incidentes obtenidos", incidentService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IncidentResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Incidente encontrado", incidentService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IncidentResponse>> create(@Valid @RequestBody IncidentRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Incidente creado", incidentService.create(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        incidentService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Incidente eliminado", null));
    }

}
