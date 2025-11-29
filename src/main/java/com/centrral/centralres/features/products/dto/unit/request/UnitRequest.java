package com.centrral.centralres.features.products.dto.unit.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnitRequest {

    @NotBlank(message = "El nombre de la unidad es obligatorio")
    private String name;

    @NotBlank(message = "El símbolo de la unidad es obligatorio")
    private String symbol;
}