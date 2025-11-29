package com.centrral.centralres.features.customers.dto.reservation.response;

import java.time.LocalDate;
import java.time.LocalTime;

import com.centrral.centralres.features.customers.enums.ReservationStatus;

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
public class ReservationResponse {
    private Long id;
    private Long customerId;
    private String customerName;
    private Long tableId;
    private String tableNumber;
    private String contactName;
    private String contactPhone;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
    private Integer numberOfPeople;
    private ReservationStatus status;
}
