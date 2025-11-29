package com.centrral.centralres.features.orders.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.centrral.centralres.features.orders.dto.orderstatus.request.OrderStatusRequest;
import com.centrral.centralres.features.orders.dto.orderstatus.response.OrderStatusResponse;
import com.centrral.centralres.features.orders.model.OrderStatus;
import com.centrral.centralres.features.orders.model.OrderStatusTranslation;
import com.centrral.centralres.features.orders.repository.OrderStatusRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderStatusService {

    private final OrderStatusRepository orderStatusRepository;

    public List<OrderStatusResponse> getAll(String lang) {
        return orderStatusRepository.findAll().stream()
                .map(status -> mapToResponse(status, lang))
                .collect(Collectors.toList());
    }

    public OrderStatusResponse getById(Long id, String lang) {
        return orderStatusRepository.findById(id)
                .map(status -> mapToResponse(status, lang))
                .orElseThrow(() -> new EntityNotFoundException("Estado de orden no encontrado con id: " + id));
    }

    public OrderStatusResponse create(OrderStatusRequest request) {
        OrderStatus status = OrderStatus.builder()
                .code(request.getCode())
                .build();

        OrderStatusTranslation translation = OrderStatusTranslation.builder()
                .orderStatus(status)
                .lang(request.getLang())
                .name(request.getName())
                .description(request.getDescription())
                .build();

        status.getTranslations().add(translation);

        return mapToResponse(orderStatusRepository.save(status), request.getLang());
    }

    public OrderStatusResponse update(Long id, OrderStatusRequest request) {
        OrderStatus status = orderStatusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estado de orden no encontrado con id: " + id));

        OrderStatusTranslation translation = status.getTranslations().stream()
                .filter(t -> t.getLang().equals(request.getLang()))
                .findFirst()
                .orElse(OrderStatusTranslation.builder()
                        .orderStatus(status)
                        .lang(request.getLang())
                        .build());

        translation.setName(request.getName());
        translation.setDescription(request.getDescription());
        status.getTranslations().add(translation);

        return mapToResponse(orderStatusRepository.save(status), request.getLang());
    }

    public void delete(Long id) {
        if (!orderStatusRepository.existsById(id)) {
            throw new EntityNotFoundException("Estado de orden no encontrado con id: " + id);
        }
        orderStatusRepository.deleteById(id);
    }

    private OrderStatusResponse mapToResponse(OrderStatus status, String lang) {
        OrderStatusTranslation translation = status.getTranslations().stream()
                .filter(t -> t.getLang().equals(lang))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Traducción no encontrada para el idioma: " + lang));

        return OrderStatusResponse.builder()
                .id(status.getId())
                .code(status.getCode())
                .name(translation.getName())
                .description(translation.getDescription())
                .lang(translation.getLang())
                .build();
    }

}
