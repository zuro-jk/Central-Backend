package com.centrral.centralres.core.security.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RefreshRequest {
    @NotBlank(message = "El refresh token es obligatorio")
    private String refreshToken;
}
