package com.centrral.centralres.features.cashclosing.dto.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CashClosingSubmitRequest {

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal countedCashAmount;

    private String notes;
}