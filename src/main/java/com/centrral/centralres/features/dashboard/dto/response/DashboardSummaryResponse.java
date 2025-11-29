package com.centrral.centralres.features.dashboard.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.centrral.centralres.features.customers.dto.reservation.response.ReservationResponse;
import com.centrral.centralres.features.customers.dto.review.response.ReviewResponse;
import com.centrral.centralres.features.products.dto.inventory.response.InventoryResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardSummaryResponse {
    private int ordersToday;
    private BigDecimal salesToday;
    private int reservationsToday;
    private List<InventoryResponse> lowStock;
    private List<ReservationResponse> upcomingReservations;
    private List<ReviewResponse> recentReviews;
    private int satisfaction;

    private Map<LocalDate, Integer> ordersWeek;
    private Map<LocalDate, BigDecimal> salesWeek;
    private Map<LocalDate, Integer> reservationsWeek;
}
