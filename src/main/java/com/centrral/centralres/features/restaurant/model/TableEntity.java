package com.centrral.centralres.features.restaurant.model;

import java.time.LocalTime;

import com.centrral.centralres.features.restaurant.enums.TableStatus;

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
@Table(name = "tables")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private Long id;

    @Column(name = "code", length = 20, nullable = false, unique = true)
    private String code;

    @Column(name = "alias", length = 100)
    private String alias;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "min_capacity", nullable = false)
    private Integer minCapacity;

    @Column(name = "optimal_capacity", nullable = false)
    private Integer optimalCapacity;

    @Column(name = "priority", nullable = false)
    private Integer priority;

    @Column(name = "description")
    private String description;

    @Column(name = "open_time", nullable = false)
    private LocalTime openTime;

    @Column(name = "close_time", nullable = false)
    private LocalTime closeTime;

    @Column(name = "reservation_duration_minutes", nullable = false)
    private Integer reservationDurationMinutes;

    @Column(name = "buffer_before_minutes", nullable = false)
    private Integer bufferBeforeMinutes;

    @Column(name = "buffer_after_minutes", nullable = false)
    private Integer bufferAfterMinutes;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TableStatus status;

    /**
     * Verifica si la mesa puede recibir la cantidad de personas indicada.
     */
    public boolean canAccommodate(int numberOfPeople) {
        return numberOfPeople >= minCapacity && numberOfPeople <= capacity;
    }

    /**
     * Indica si la mesa es ideal para la cantidad de personas (para asignación
     * automática).
     */
    public boolean isOptimalFor(int numberOfPeople) {
        return numberOfPeople <= optimalCapacity;
    }

}
