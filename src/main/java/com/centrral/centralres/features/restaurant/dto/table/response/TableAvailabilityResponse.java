package com.centrral.centralres.features.restaurant.dto.table.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableAvailabilityResponse {
    private Long id;
    private String name;
    private Integer capacity;
    private Integer minCapacity;
    private List<String> availableTimes;
}
