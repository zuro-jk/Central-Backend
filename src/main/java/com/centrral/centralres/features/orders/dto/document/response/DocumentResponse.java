package com.centrral.centralres.features.orders.dto.document.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentResponse {
    private Long id;
    private Long orderId;
    private String type;
    private String number;
    private BigDecimal amount;
    private LocalDateTime date;
}