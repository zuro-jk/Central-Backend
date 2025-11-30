package com.centrral.centralres.features.orders.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.centrral.centralres.core.config.NiubizConfig;
import com.centrral.centralres.core.security.repository.PaymentProfileRepository;
import com.centrral.centralres.features.orders.dto.niubiz.NiubizDTO;
import com.centrral.centralres.features.orders.dto.payment.request.PaymentInOrderRequest;
import com.centrral.centralres.features.orders.dto.payment.request.PaymentStartRequest;
import com.centrral.centralres.features.orders.dto.payment.request.PaymentUpdateRequest;
import com.centrral.centralres.features.orders.dto.payment.response.PaymentResponse;
import com.centrral.centralres.features.orders.enums.PaymentStatus;
import com.centrral.centralres.features.orders.model.Order;
import com.centrral.centralres.features.orders.model.Payment;
import com.centrral.centralres.features.orders.model.PaymentMethod;
import com.centrral.centralres.features.orders.repository.OrderRepository;
import com.centrral.centralres.features.orders.repository.OrderStatusRepository;
import com.centrral.centralres.features.orders.repository.PaymentMethodRepository;
import com.centrral.centralres.features.orders.repository.PaymentRepository;
import com.centrral.centralres.features.orders.specifications.PaymentSpecification;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

        private final PaymentRepository paymentRepository;
        private final OrderRepository orderRepository;
        private final PaymentMethodRepository paymentMethodRepository;
        private final PaymentProfileRepository paymentProfileRepository;
        private final OrderStatusRepository orderStatusRepository;
        private final NiubizService niubizService;
        private final NiubizConfig niubizConfig;

        public List<PaymentResponse> getAll(
                        Long customerId, Long paymentMethodId, LocalDateTime dateFrom, LocalDateTime dateTo) {

                Specification<Payment> spec = PaymentSpecification.withFilters(
                                customerId, paymentMethodId, dateFrom, dateTo);

                return paymentRepository.findAll(spec).stream()
                                .map(this::mapToResponse)
                                .toList();
        }

        public PaymentResponse getById(Long id) {
                Payment payment = paymentRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con id: " + id));
                return mapToResponse(payment);
        }

        @Transactional(readOnly = true)
        public NiubizDTO.PaymentStartResponse startOnlinePayment(PaymentStartRequest request) {

                log.info("Iniciando sesión de pago Niubiz para orderId: {}", request.getOrderId());

                Order order = orderRepository.findById(request.getOrderId())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Orden no encontrada: " + request.getOrderId()));

                validateOrderForPayment(order);

                // --- CORRECCIÓN CRÍTICA: FORMATO PURCHASE NUMBER ---
                // Niubiz exige: Numérico y Max 12 dígitos.
                // Si tu ID de orden es 150, esto enviará "150".
                // Si necesitas unicidad por intentos fallidos, puedes usar una lógica más
                // compleja
                // pero para empezar, usa el ID directo.
                String purchaseNumber = String.valueOf(order.getId());

                // OPCIONAL: Si necesitas reintentos, podrías usar:
                // (orderId % 100000) + "" + (System.currentTimeMillis() % 1000000);
                // pero intenta primero con el ID limpio.

                if (purchaseNumber.length() > 12) {
                        throw new RuntimeException("El ID de la orden excede los 12 dígitos permitidos por Niubiz");
                }
                // ----------------------------------------------------

                NiubizDTO.PaymentStartResponse niubizResponse = niubizService.createPaymentSession(
                                request.getAmount(),
                                purchaseNumber);

                return NiubizDTO.PaymentStartResponse.builder()
                                .sessionKey(niubizResponse.getSessionKey())
                                .expirationTime(niubizResponse.getExpirationTime())
                                .merchantId(niubizConfig.getMerchant().getId())
                                .purchaseNumber(purchaseNumber)
                                .amount(request.getAmount())
                                .build();
        }

        @Transactional
        public PaymentResponse confirmOnlinePayment(NiubizDTO.PaymentConfirmRequest request) {

                log.info("Confirmando pago Niubiz");

                // Extraer ID de la orden del purchaseNumber (ej: "105-17482348")
                Long orderId = Long.parseLong(request.getPurchaseNumber().split("-")[0]);

                Order order = orderRepository.findById(orderId)
                                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada ID: " + orderId));

                // --- CORRECCIÓN 2: Lógica de autorización ---
                // authorizePayment devuelve boolean, y recibe BigDecimal
                boolean isAuthorized = niubizService.authorizePayment(
                                request.getTransactionToken(),
                                request.getAmount(), // Pasar BigDecimal directo
                                request.getPurchaseNumber());

                if (!isAuthorized) {
                        throw new RuntimeException("El pago fue rechazado o no autorizado por Niubiz");
                }

                PaymentMethod method = paymentMethodRepository.findByCodeAndProvider("CARD", "NIUBIZ")
                                .orElseThrow(() -> new EntityNotFoundException("Método de pago NIUBIZ no configurado"));

                Payment payment = Payment.builder()
                                .order(order)
                                .paymentMethod(method)
                                .amount(request.getAmount())
                                .isOnline(true)
                                .transactionCode(request.getTransactionToken())
                                .status(PaymentStatus.CONFIRMED)
                                .date(LocalDateTime.now())
                                .build();

                Payment saved = paymentRepository.save(payment);

                // Opcional: Actualizar estado de la orden a PAGADO aquí si es necesario
                // order.setStatus(...);
                // orderRepository.save(order);

                return mapToResponse(saved);
        }

        @Transactional
        public void createInOrder(Order order, PaymentInOrderRequest request) {
                PaymentMethod method = paymentMethodRepository.findById(request.getPaymentMethodId())
                                .orElseThrow(() -> new EntityNotFoundException(
                                                "Método de pago no encontrado con id: "
                                                                + request.getPaymentMethodId()));

                PaymentStatus defaultStatus = request.getIsOnline()
                                ? PaymentStatus.PENDING
                                : PaymentStatus.CONFIRMED;

                Payment payment = Payment.builder()
                                .order(order)
                                .paymentMethod(method)
                                .amount(request.getAmount())
                                .date(LocalDateTime.now())
                                .isOnline(request.getIsOnline())
                                .transactionCode(
                                                request.getTransactionCode() != null
                                                                ? request.getTransactionCode()
                                                                : UUID.randomUUID().toString())
                                .status(request.getStatus() != null ? request.getStatus() : defaultStatus)
                                .build();

                order.getPayments().add(payment);
        }

        @Transactional
        public PaymentResponse update(Long id, PaymentUpdateRequest request) {
                Payment payment = paymentRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con id: " + id));

                if (request.getTransactionCode() != null) {
                        payment.setTransactionCode(request.getTransactionCode());
                }
                if (request.getStatus() != null) {
                        payment.setStatus(request.getStatus());
                }

                return mapToResponse(paymentRepository.save(payment));
        }

        @Transactional
        public void delete(Long id) {
                Payment payment = paymentRepository.findById(id)
                                .orElseThrow(() -> new EntityNotFoundException("Pago no encontrado con id: " + id));

                payment.setStatus(PaymentStatus.CANCELLED);
                paymentRepository.save(payment);
        }

        private void validateOrderForPayment(Order order) {
                if (order.getType() == null) {
                        throw new IllegalArgumentException("El tipo de orden es nulo.");
                }
                String orderTypeCode = order.getType().getCode().toUpperCase();
                if (!"DELIVERY".equals(orderTypeCode) && !"TAKE_AWAY".equals(orderTypeCode)) {
                        throw new IllegalArgumentException("Pagos online no permitidos para: " + orderTypeCode);
                }
                if (order.getTotal() == null || order.getTotal().compareTo(BigDecimal.ZERO) <= 0) {
                        throw new IllegalArgumentException("El monto a pagar debe ser mayor a cero.");
                }
        }

        private PaymentResponse mapToResponse(Payment payment) {
                return PaymentResponse.builder()
                                .id(payment.getId())
                                .orderId(payment.getOrder().getId())
                                .paymentMethodId(payment.getPaymentMethod().getId())
                                .paymentMethodName(payment.getPaymentMethod().getCode())
                                .customerName(payment.getOrder().getCustomer().getUser().getFullName())
                                .amount(payment.getAmount())
                                .date(payment.getDate())
                                .isOnline(payment.getIsOnline())
                                .transactionCode(payment.getTransactionCode())
                                .provider(payment.getPaymentMethod().getProvider())
                                .status(payment.getStatus())
                                .build();
        }

}
