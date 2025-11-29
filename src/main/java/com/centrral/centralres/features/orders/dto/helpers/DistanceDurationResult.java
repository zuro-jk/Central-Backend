package com.centrral.centralres.features.orders.dto.helpers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DistanceDurationResult {
    private double distanceKm;
    private double durationMinutes;
}