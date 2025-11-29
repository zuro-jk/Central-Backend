package com.centrral.centralres.features.feedbackloyalty.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.centrral.centralres.features.customers.enums.PointHistoryEventType;
import com.centrral.centralres.features.customers.model.Customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "points_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointsHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private Integer points;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PointHistoryEventType event;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

}
