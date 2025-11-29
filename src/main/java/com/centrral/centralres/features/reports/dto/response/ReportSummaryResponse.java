package com.centrral.centralres.features.reports.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSummaryResponse {
    private Long totalOrders;
    private BigDecimal totalSales;

    private Integer ordersToday;
    private BigDecimal salesToday;

    private List<ProductSalesReportResponse> topProducts;

    private List<OrderTypeReportResponse> orderTypes;

    private Map<LocalDate, BigDecimal> salesLast7Days;

    private Map<LocalDate, Integer> ordersLast7Days;
}