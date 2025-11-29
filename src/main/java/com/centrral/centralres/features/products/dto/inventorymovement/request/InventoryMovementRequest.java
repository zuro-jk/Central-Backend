package com.centrral.centralres.features.products.dto.inventorymovement.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.centrral.centralres.features.products.enums.MovementSource;
import com.centrral.centralres.features.products.enums.MovementType;

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
public class InventoryMovementRequest {

    @NotNull(message = "El ID del ingrediente es obligatorio")
    private Long ingredientId;

    @NotNull(message = "El tipo de movimiento es obligatorio")
    private MovementType type;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser mayor a 0")
    private BigDecimal quantity;

    private String reason;

    private LocalDateTime date;

    @NotNull(message = "El origen del movimiento es obligatorio")
    private MovementSource source;

    private Long referenceId;
}