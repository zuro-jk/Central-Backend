package com.centrral.centralres.features.products.controller;

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
import com.centrral.centralres.features.products.dto.ingredient.request.IngredientRequest;
import com.centrral.centralres.features.products.dto.ingredient.response.IngredientResponse;
import com.centrral.centralres.features.products.service.IngredientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/ingredients")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<IngredientResponse>>> getAll() {
        List<IngredientResponse> ingredients = ingredientService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingredientes obtenidos con éxito", ingredients));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IngredientResponse>> getById(@PathVariable Long id) {
        IngredientResponse ingredient = ingredientService.findById(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingrediente encontrado", ingredient));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IngredientResponse>> create(@Valid @RequestBody IngredientRequest request) {
        IngredientResponse created = ingredientService.create(request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingrediente creado con éxito", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IngredientResponse>> update(@PathVariable Long id,
            @Valid @RequestBody IngredientRequest request) {
        IngredientResponse updated = ingredientService.update(id, request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingrediente actualizado con éxito", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        ingredientService.delete(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Ingrediente eliminado con éxito", null));
    }

}
