package com.centrral.centralres.core.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    private String sessionId;
    private UserProfileResponse user;
}
