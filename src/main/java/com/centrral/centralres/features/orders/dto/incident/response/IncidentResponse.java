package com.centrral.centralres.features.orders.dto.incident.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IncidentResponse {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long productId;
    private Long supplierId;
    private String type;
    private String description;
    private LocalDateTime date;
    private String status;
}