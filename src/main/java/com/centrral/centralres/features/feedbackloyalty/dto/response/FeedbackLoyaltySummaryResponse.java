package com.centrral.centralres.features.feedbackloyalty.dto.response;

import java.util.List;

import com.centrral.centralres.features.customers.dto.review.response.ReviewResponse;
import com.centrral.centralres.features.feedbackloyalty.dto.reward.response.RewardResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackLoyaltySummaryResponse {
    private List<ReviewResponse> recentReviews;
    private double averageRating;

    private long totalRegisteredCustomers;
    private int totalPointsAccumulated;
    private int totalPointsRedeemed;

    private List<LoyalCustomerResponse> topLoyalCustomers;
    private List<RewardResponse> availableRewards;
}
