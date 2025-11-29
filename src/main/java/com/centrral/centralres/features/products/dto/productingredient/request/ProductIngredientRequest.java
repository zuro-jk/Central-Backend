package com.centrral.centralres.features.products.dto.productingredient.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
public class ProductIngredientRequest {
    @NotNull(message = "El ID del ingrediente es obligatorio")
    private Long ingredientId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 0, message = "La cantidad debe ser mayor o igual a 0")
    private Double quantity;
}
