package com.centrral.centralres.features.orders.dto.niubiz;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class NiubizDTO {

    // --- 1. DTO para mapear la respuesta RAW de la API de Niubiz ---
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SessionResponse {
        // La documentación oficial dice "sessionKey", no "sessionToken"
        @JsonProperty("sessionKey")
        private String sessionKey;

        @JsonProperty("expirationTime")
        private Long expirationTime;
    }

    // --- 2. DTO para responder a tu Frontend (Angular) ---
    @Data
    @Builder
    public static class PaymentStartResponse {
        private String sessionKey; // Este valor irá en el HTML como data-sessiontoken
        private String merchantId;
        private String purchaseNumber;
        private BigDecimal amount;
        private Long expirationTime;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentStartRequest {
        private Long orderId;
        private BigDecimal amount;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PaymentConfirmRequest {
        private String transactionToken;
        private String purchaseNumber;
        private BigDecimal amount;
    }
}