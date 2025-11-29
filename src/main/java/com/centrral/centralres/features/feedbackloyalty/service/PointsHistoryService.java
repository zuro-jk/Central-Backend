package com.centrral.centralres.features.feedbackloyalty.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.centrral.centralres.core.exceptions.InvalidPointsOperationException;
import com.centrral.centralres.features.customers.enums.PointHistoryEventType;
import com.centrral.centralres.features.customers.model.Customer;
import com.centrral.centralres.features.customers.repository.CustomerRepository;
import com.centrral.centralres.features.feedbackloyalty.models.PointsHistory;
import com.centrral.centralres.features.feedbackloyalty.repository.PointsHistoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointsHistoryService {

    private final CustomerRepository customerRepository;
    private final PointsHistoryRepository pointsHistoryRepository;

    /**
     * Aplica puntos a un cliente y guarda el historial.
     */
    @Transactional
    public int applyPoints(Customer customer, int points, PointHistoryEventType eventType) {
        if (points <= 0) {
            throw new InvalidPointsOperationException("No se pueden aplicar puntos <= 0");
        }

        customer.setPoints(customer.getPoints() + points);
        customerRepository.save(customer);

        PointsHistory history = PointsHistory.builder()
                .customer(customer)
                .points(points)
                .event(eventType)
                .build();
        pointsHistoryRepository.save(history);

        return points;
    }

    /**
     * Obtiene el historial de puntos de un cliente paginado.
     */
    @Transactional(readOnly = true)
    public Page<PointsHistory> getHistoryByCustomer(Customer customer, Pageable pageable) {
        return pointsHistoryRepository.findByCustomerId(customer.getId(), pageable);
    }

}
