package com.centrral.centralres.features.restaurant.dto.table.response;

import java.time.LocalTime;

import com.centrral.centralres.features.restaurant.enums.TableStatus;

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
public class TableResponse {
    private Long id;
    private String code;
    private String alias;
    private Integer capacity;
    private Integer minCapacity;
    private Integer optimalCapacity;
    private Integer priority;
    private String description;
    private LocalTime openTime;
    private LocalTime closeTime;
    private Integer reservationDurationMinutes;
    private Integer bufferBeforeMinutes;
    private Integer bufferAfterMinutes;
    private TableStatus status;

    private Long activeOrderId;
}
