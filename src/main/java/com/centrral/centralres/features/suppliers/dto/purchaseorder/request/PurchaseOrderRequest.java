package com.centrral.centralres.features.suppliers.dto.purchaseorder.request;

import java.util.List;

import com.centrral.centralres.features.suppliers.dto.purchaseorderdetail.request.PurchaseOrderDetailCreateRequest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PurchaseOrderRequest {
    @NotNull(message = "El proveedor es obligatorio")
    private Long supplierId;

    private String status;

    @NotNull(message = "Debe incluir al menos un detalle")
    @Valid
    private List<PurchaseOrderDetailCreateRequest> details;
}
