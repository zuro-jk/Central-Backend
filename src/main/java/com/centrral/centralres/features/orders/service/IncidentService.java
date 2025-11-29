package com.centrral.centralres.features.orders.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.centrral.centralres.core.exceptions.BadRequestException;
import com.centrral.centralres.core.security.model.User;
import com.centrral.centralres.core.security.repository.UserRepository;
import com.centrral.centralres.features.orders.dto.incident.request.IncidentRequest;
import com.centrral.centralres.features.orders.dto.incident.response.IncidentResponse;
import com.centrral.centralres.features.orders.enums.IncidentStatus;
import com.centrral.centralres.features.orders.enums.IncidentType;
import com.centrral.centralres.features.orders.model.Incident;
import com.centrral.centralres.features.orders.model.Order;
import com.centrral.centralres.features.orders.repository.IncidentRepository;
import com.centrral.centralres.features.orders.repository.OrderRepository;
import com.centrral.centralres.features.products.model.Product;
import com.centrral.centralres.features.products.repository.ProductRepository;
import com.centrral.centralres.features.suppliers.model.Supplier;
import com.centrral.centralres.features.suppliers.repository.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    public List<IncidentResponse> getAll() {
        return incidentRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public IncidentResponse getById(Long id) {
        return incidentRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new EntityNotFoundException("Incidente no encontrado con id: " + id));
    }

    @Transactional
    public IncidentResponse create(IncidentRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Order order = request.getOrderId() != null
                ? orderRepository.findById(request.getOrderId())
                        .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada"))
                : null;

        Product product = request.getProductId() != null
                ? productRepository.findById(request.getProductId())
                        .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"))
                : null;

        Supplier supplier = request.getSupplierId() != null
                ? supplierRepository.findById(request.getSupplierId())
                        .orElseThrow(() -> new EntityNotFoundException("Proveedor no encontrado"))
                : null;

        IncidentType type;
        try {
            type = IncidentType.valueOf(request.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Tipo de incidente inválido. Valores permitidos: " +
                    String.join(", ",
                            java.util.Arrays.stream(IncidentType.values())
                                    .map(Enum::name)
                                    .toList()));
        }

        switch (type) {
            case ORDER -> {
                if (request.getOrderId() == null) {
                    throw new BadRequestException("Para un incidente de tipo ORDER, se debe especificar orderId");
                }
            }
            case PRODUCT -> {
                if (request.getProductId() == null) {
                    throw new BadRequestException("Para un incidente de tipo PRODUCT, se debe especificar productId");
                }
            }
            case SUPPLIER -> {
                if (request.getSupplierId() == null) {
                    throw new BadRequestException("Para un incidente de tipo SUPPLIER, se debe especificar supplierId");
                }
            }
        }

        IncidentStatus status = request.getStatus() != null
                ? IncidentStatus.valueOf(request.getStatus().toUpperCase())
                : IncidentStatus.OPEN;

        Incident incident = Incident.builder()
                .user(user)
                .order(order)
                .product(product)
                .supplier(supplier)
                .type(type)
                .description(request.getDescription())
                .status(status)
                .date(LocalDateTime.now())
                .build();

        Incident saved = incidentRepository.save(incident);

        return mapToResponse(saved);
    }

    public void delete(Long id) {
        if (!incidentRepository.existsById(id)) {
            throw new EntityNotFoundException("Incidente no encontrado con id: " + id);
        }
        incidentRepository.deleteById(id);
    }

    private IncidentResponse mapToResponse(Incident incident) {
        return IncidentResponse.builder()
                .id(incident.getId())
                .userId(incident.getUser().getId())
                .orderId(incident.getOrder() != null ? incident.getOrder().getId() : null)
                .productId(incident.getProduct() != null ? incident.getProduct().getId() : null)
                .supplierId(incident.getSupplier() != null ? incident.getSupplier().getId() : null)
                .type(incident.getType().name())
                .description(incident.getDescription())
                .date(incident.getDate())
                .status(incident.getStatus().name())
                .build();
    }

}
