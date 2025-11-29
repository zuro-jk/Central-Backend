package com.centrral.centralres.features.products.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.centrral.centralres.features.products.exceptions.InsufficientStockException;
import com.centrral.centralres.features.products.exceptions.InvalidQuantityException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "inventories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "ingredient_id", nullable = false, unique = true)
    private Ingredient ingredient;

    @Column(name = "current_stock", nullable = false, precision = 12, scale = 2)
    private BigDecimal currentStock;

    @Column(name = "minimum_stock", nullable = false, precision = 12, scale = 2)
    private BigDecimal minimumStock;

    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Version
    private Long version;

    /**
     * Evita spam de alertas: se guarda la fecha en que se envió la última
     * notificación
     */
    @Column
    private LocalDateTime alertSentAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (updatedAt == null)
            updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public void increaseStock(BigDecimal qty) {
        if (qty.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidQuantityException("Cantidad debe ser positiva");
        this.currentStock = this.currentStock.add(qty);
    }

    public void decreaseStock(BigDecimal qty) {
        if (qty.compareTo(BigDecimal.ZERO) < 0)
            throw new InvalidQuantityException("Cantidad debe ser positiva");

        if (this.currentStock.compareTo(qty) < 0)
            throw new InsufficientStockException(
                    "Stock insuficiente. Disponible: " + this.currentStock + ", solicitado: " + qty);

        this.currentStock = this.currentStock.subtract(qty);
    }

    public boolean shouldNotifyLowStock() {
        if (currentStock.compareTo(minimumStock) >= 0)
            return false;
        if (alertSentAt == null)
            return true;
        return alertSentAt.plusHours(24).isBefore(LocalDateTime.now());
    }

    public void markAlertSent() {
        this.alertSentAt = LocalDateTime.now();
    }

}