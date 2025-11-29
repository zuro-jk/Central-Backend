package com.centrral.centralres.features.orders.dto.order.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
public class OrderCreatedEvent {
    private Long orderId;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private List<ProductInfo> products;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ProductInfo {
        private String name;
        private BigDecimal unitPrice;
        private int quantity;
    }
}
