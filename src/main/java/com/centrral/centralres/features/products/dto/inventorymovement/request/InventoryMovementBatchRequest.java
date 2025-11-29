package com.centrral.centralres.features.products.dto.inventorymovement.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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
public class InventoryMovementBatchRequest {

    @NotEmpty(message = "Debe proporcionar al menos un movimiento")
    @Valid
    private List<InventoryMovementRequest> movements;
}