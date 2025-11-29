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
import com.centrral.centralres.features.orders.dto.document.request.DocumentRequest;
import com.centrral.centralres.features.orders.dto.document.response.DocumentResponse;
import com.centrral.centralres.features.orders.service.DocumentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<DocumentResponse>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Lista de documentos", documentService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Documento encontrado", documentService.getById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<DocumentResponse>> create(@Valid @RequestBody DocumentRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Documento creado", documentService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<DocumentResponse>> update(@PathVariable Long id,
            @Valid @RequestBody DocumentRequest request) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Documento actualizado", documentService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        documentService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Documento eliminado", null));
    }

}
