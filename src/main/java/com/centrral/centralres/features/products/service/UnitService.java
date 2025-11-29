package com.centrral.centralres.features.products.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.centrral.centralres.features.products.dto.unit.request.UnitRequest;
import com.centrral.centralres.features.products.dto.unit.response.UnitResponse;
import com.centrral.centralres.features.products.model.Unit;
import com.centrral.centralres.features.products.repository.UnitRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitResponse create(UnitRequest request) {
        Unit unit = Unit.builder()
                .name(request.getName())
                .symbol(request.getSymbol())
                .build();

        Unit saved = unitRepository.save(unit);

        return toResponse(saved);
    }

    public List<UnitResponse> findAll() {
        return unitRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UnitResponse getById(Long id) {
        return unitRepository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new RuntimeException("Unidad no encontrada"));
    }

    private UnitResponse toResponse(Unit unit) {
        return UnitResponse.builder()
                .id(unit.getId())
                .name(unit.getName())
                .symbol(unit.getSymbol())
                .build();
    }

}
