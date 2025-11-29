package com.centrral.centralres.features.dashboard.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.centrral.centralres.features.customers.dto.reservation.response.ReservationResponse;
import com.centrral.centralres.features.customers.dto.review.response.ReviewResponse;
import com.centrral.centralres.features.customers.service.ReservationService;
import com.centrral.centralres.features.customers.service.ReviewService;
import com.centrral.centralres.features.dashboard.dto.response.DashboardSummaryResponse;
import com.centrral.centralres.features.orders.service.OrderService;
import com.centrral.centralres.features.products.dto.inventory.response.InventoryResponse;
import com.centrral.centralres.features.products.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final OrderService orderService;
    private final ReservationService reservationService;
    private final ReviewService reviewService;
    private final InventoryService inventoryService;

    public DashboardSummaryResponse getDashboardSummary() {

        LocalDate today = LocalDate.now();

        int ordersToday = orderService.countOrdersByDate(today);
        BigDecimal salesToday = orderService.calculateSalesByDate(today);
        int reservationsToday = reservationService.countReservationsByDate(today);

        List<InventoryResponse> lowStock = inventoryService.findLowStockInventories();
        List<ReservationResponse> upcomingReservations = reservationService.findUpcomingReservations();
        List<ReviewResponse> recentReviews = reviewService.findRecentReviews(5);

        int satisfaction = reviewService.calculateAverageSatisfaction();

        Map<LocalDate, Integer> ordersWeek = orderService.countOrdersLast7Days();
        Map<LocalDate, BigDecimal> salesWeek = orderService.calculateSalesLast7Days();
        Map<LocalDate, Integer> reservationsWeek = reservationService.countReservationsNext7Days();

        return new DashboardSummaryResponse(
                ordersToday,
                salesToday,
                reservationsToday,
                lowStock,
                upcomingReservations,
                recentReviews,
                satisfaction,
                ordersWeek,
                salesWeek,
                reservationsWeek);
    }

}
