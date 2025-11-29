package com.centrral.centralres.features.orders.dto.payment.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.centrral.centralres.features.orders.enums.PaymentStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponse {
    private Long id;
    private Long orderId;
    private Long paymentMethodId;
    private String paymentMethodName;
    private String customerName;
    private BigDecimal amount;
    private LocalDateTime date;
    private Boolean isOnline;
    private String transactionCode;
    private String provider;
    private PaymentStatus status;
}