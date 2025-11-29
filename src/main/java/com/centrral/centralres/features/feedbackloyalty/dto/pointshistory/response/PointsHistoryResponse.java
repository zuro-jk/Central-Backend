package com.centrral.centralres.features.feedbackloyalty.dto.pointshistory.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PointsHistoryResponse {
    private int points;
    private String event;
    private LocalDateTime createdAt;
}