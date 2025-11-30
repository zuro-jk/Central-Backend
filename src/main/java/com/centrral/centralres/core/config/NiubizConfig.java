package com.centrral.centralres.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "niubiz")
public class NiubizConfig {

    private Api api;
    private Merchant merchant;

    @Data
    public static class Api {
        private String security;
        private String session;
        private String authorization;
    }

    @Data
    public static class Merchant {
        private String id;
        private String user;
        private String password;
    }
}