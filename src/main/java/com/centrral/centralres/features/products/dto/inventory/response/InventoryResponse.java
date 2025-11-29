package com.centrral.centralres.features.products.dto.inventory.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class InventoryResponse {
    private Long id;
    private Long ingredientId;
    private String ingredientName;
    private String unitName;
    private String unitSymbol;
    private BigDecimal currentStock;
    private BigDecimal minimumStock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}