package com.centrral.centralres.features.orders.model;

import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment_methods", uniqueConstraints = {
                @UniqueConstraint(columnNames = { "code", "provider" })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentMethod {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "payment_method_id")
        private Long id;

        @Column(name = "code", nullable = false, length = 50)
        private String code;

        @Column(name = "provider", nullable = false, length = 50)
        private String provider;

        @OneToMany(mappedBy = "paymentMethod", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<PaymentMethodTranslation> translations = new LinkedHashSet<>();
}