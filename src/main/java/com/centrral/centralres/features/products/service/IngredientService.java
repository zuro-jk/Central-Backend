package com.centrral.centralres.features.products.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.centrral.centralres.features.products.dto.ingredient.request.IngredientRequest;
import com.centrral.centralres.features.products.dto.ingredient.response.IngredientResponse;
import com.centrral.centralres.features.products.exceptions.IngredientNotFoundException;
import com.centrral.centralres.features.products.exceptions.UnitNotFoundException;
import com.centrral.centralres.features.products.model.Ingredient;
import com.centrral.centralres.features.products.model.Unit;
import com.centrral.centralres.features.products.repository.IngredientRepository;
import com.centrral.centralres.features.products.repository.UnitRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IngredientService {

    private final IngredientRepository ingredientRepository;
    private final UnitRepository unitRepository;

    public List<IngredientResponse> findAll() {
        return ingredientRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public IngredientResponse findById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Ingrediente no encontrado con id: " + id));
        return toResponse(ingredient);
    }

    public IngredientResponse create(IngredientRequest request) {
        Unit unit = unitRepository.findById(request.getUnitId())
                .orElseThrow(() -> new UnitNotFoundException("Unidad no encontrada con id: " + request.getUnitId()));

        Ingredient ingredient = Ingredient.builder()
                .name(request.getName())
                .unit(unit)
                .build();

        return toResponse(ingredientRepository.save(ingredient));
    }

    public IngredientResponse update(Long id, IngredientRequest request) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Ingrediente no encontrado con id: " + id));

        ingredient.setName(request.getName());

        if (request.getUnitId() != null) {
            Unit unit = unitRepository.findById(request.getUnitId())
                    .orElseThrow(
                            () -> new UnitNotFoundException("Unidad no encontrada con id: " + request.getUnitId()));
            ingredient.setUnit(unit);
        }

        return toResponse(ingredientRepository.save(ingredient));
    }

    public void delete(Long id) {
        if (!ingredientRepository.existsById(id)) {
            throw new IngredientNotFoundException("Ingrediente no encontrado con id: " + id);
        }
        ingredientRepository.deleteById(id);
    }

    private IngredientResponse toResponse(Ingredient ingredient) {
        return IngredientResponse.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .unitId(ingredient.getUnit().getId())
                .unitName(ingredient.getUnit().getName())
                .unitSymbol(ingredient.getUnit().getSymbol())
                .build();
    }

}
