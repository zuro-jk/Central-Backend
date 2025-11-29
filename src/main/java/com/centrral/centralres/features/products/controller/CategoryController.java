package com.centrral.centralres.features.products.controller;

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
import com.centrral.centralres.features.products.dto.category.request.CategoryRequest;
import com.centrral.centralres.features.products.dto.category.response.CategoryResponse;
import com.centrral.centralres.features.products.service.CategoryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryResponse>>> getAll() {
        List<CategoryResponse> categories = categoryService.getAll();
        return ResponseEntity.ok(new ApiResponse<>(true, "Categorías obtenidas", categories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable Long id) {
        CategoryResponse category = categoryService.getById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Categoría obtenida", category));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryRequest request) {
        CategoryResponse category = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Categoría creada", category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(@PathVariable Long id,
            @Valid @RequestBody CategoryRequest request) {
        CategoryResponse category = categoryService.update(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Categoría actualizada", category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Categoría eliminada", null));
    }
}