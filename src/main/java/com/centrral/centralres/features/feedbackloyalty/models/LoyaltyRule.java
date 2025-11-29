package com.centrral.centralres.features.feedbackloyalty.models;

import com.centrral.centralres.features.customers.enums.LoyaltyRuleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "loyalty_rules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoyaltyRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false)
    private Integer points;

    private Double minPurchaseAmount;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private boolean perPerson;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoyaltyRuleType type;
}