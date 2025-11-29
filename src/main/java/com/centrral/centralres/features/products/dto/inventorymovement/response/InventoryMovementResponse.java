package com.centrral.centralres.features.products.dto.inventorymovement.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.centrral.centralres.features.products.enums.MovementSource;
import com.centrral.centralres.features.products.enums.MovementType;

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
public class InventoryMovementResponse {
    private Long id;
    private Long ingredientId;
    private String ingredientName;
    private String unitName;
    private String unitSymbol;
    private MovementType type;
    private BigDecimal quantity;
    private LocalDateTime date;
    private String reason;
    private MovementSource source;
    private Long referenceId;
    private LocalDateTime createdAt;
}