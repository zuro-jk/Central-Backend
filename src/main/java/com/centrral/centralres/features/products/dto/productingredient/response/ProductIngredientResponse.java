package com.centrral.centralres.features.products.dto.productingredient.response;

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
public class ProductIngredientResponse {
    private Long ingredientId;
    private String ingredientName;
    private String unitName;
    private String unitSymbol;
    private Double quantity;
}