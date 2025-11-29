package com.centrral.centralres.features.customers.dto.reservation.response;

import java.time.LocalDate;
import java.time.LocalTime;

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
public class ReservationCreatedEvent {
    private Long reservationId;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private int numberOfPeople;
    private LocalDate reservationDate;
    private LocalTime reservationTime;
}