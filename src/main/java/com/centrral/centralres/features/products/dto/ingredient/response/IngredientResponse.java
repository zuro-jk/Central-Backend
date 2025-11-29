package com.centrral.centralres.features.products.dto.ingredient.response;

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
public class IngredientResponse {
    private Long id;
    private String name;
    private Long unitId;
    private String unitName;
    private String unitSymbol;
}