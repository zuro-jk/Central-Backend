package com.centrral.centralres.core.email.dto.resposne;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailMessageResponse {
    private boolean success;
    private String message;
}