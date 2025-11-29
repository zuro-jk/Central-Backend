package com.centrral.centralres.features.products.dto.unit.response;

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
public class UnitResponse {
    private Long id;
    private String name;
    private String symbol;
}