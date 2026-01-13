package com.goeasy.teste.service;

import com.goeasy.teste.dto.PaymentRequest;
import com.goeasy.teste.dto.PaymentResponse;
import com.goeasy.teste.exception.PaymentNotFoundException;
import com.goeasy.teste.model.Payment;
import com.goeasy.teste.model.PaymentStatus;
import com.goeasy.teste.repository.PaymentRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    
    private final PaymentRepository paymentRepository;
    
    /**
     * Cria um novo pagamento e Payment Intent na Stripe
     */
    @Transactional
    public PaymentResponse createPayment(PaymentRequest request) throws StripeException {
        log.info("Creating payment for amount: {} {}", request.getAmount(), request.getCurrency());
        
        // Converter valor para centavos (Stripe trabalha com a menor unidade da moeda)
        long amountInCents = request.getAmount()
            .multiply(new BigDecimal("100"))
            .longValue();
        
        // Criar PaymentIntent na Stripe
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(amountInCents)
            .setCurrency(request.getCurrency().toLowerCase())
            .setDescription(request.getDescription())
            .putMetadata("customer_email", request.getCustomerEmail())
            .setAutomaticPaymentMethods(
                PaymentIntentCreateParams.AutomaticPaymentMethods.builder()
                    .setEnabled(true)
                    .build()
            )
            .build();
        
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        
        // Salvar pagamento no banco de dados
        Payment payment = new Payment();
        payment.setStripePaymentIntentId(paymentIntent.getId());
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setCustomerEmail(request.getCustomerEmail());
        payment.setDescription(request.getDescription());
        payment.setStatus(PaymentStatus.PENDING);
        
        Payment savedPayment = paymentRepository.save(payment);
        
        log.info("Payment created successfully with ID: {}", savedPayment.getId());
        
        return mapToResponse(savedPayment, paymentIntent.getClientSecret());
    }
    
    /**
     * Busca um pagamento por ID
     */
    public PaymentResponse getPayment(Long id) {
        log.info("Fetching payment with ID: {}", id);
        
        Payment payment = paymentRepository.findById(id)
            .orElseThrow(() -> new PaymentNotFoundException(id));
        
        return mapToResponse(payment, null);
    }
    
    /**
     * Lista todos os pagamentos
     */
    public List<PaymentResponse> getAllPayments() {
        log.info("Fetching all payments");
        
        return paymentRepository.findAll().stream()
            .map(payment -> mapToResponse(payment, null))
            .collect(Collectors.toList());
    }
    
    /**
     * Busca pagamentos por email do cliente
     */
    public List<PaymentResponse> getPaymentsByCustomer(String email) {
        log.info("Fetching payments for customer: {}", email);
        
        return paymentRepository.findByCustomerEmail(email).stream()
            .map(payment -> mapToResponse(payment, null))
            .collect(Collectors.toList());
    }
    
    /**
     * Atualiza status do pagamento (seria chamado por webhook)
     */
    @Transactional
    public PaymentResponse updatePaymentStatus(String paymentIntentId, PaymentStatus status) {
        log.info("Updating payment status for Intent: {} to {}", paymentIntentId, status);
        
        Payment payment = paymentRepository.findByStripePaymentIntentId(paymentIntentId)
            .orElseThrow(() -> new PaymentNotFoundException("Payment not found with Intent ID: " + paymentIntentId));
        
        payment.setStatus(status);
        Payment updatedPayment = paymentRepository.save(payment);
        
        return mapToResponse(updatedPayment, null);
    }
    
    /**
     * Mapeia Payment entity para PaymentResponse DTO
     */
    private PaymentResponse mapToResponse(Payment payment, String clientSecret) {
        return PaymentResponse.builder()
            .id(payment.getId())
            .paymentIntentId(payment.getStripePaymentIntentId())
            .amount(payment.getAmount())
            .currency(payment.getCurrency())
            .customerEmail(payment.getCustomerEmail())
            .status(payment.getStatus())
            .description(payment.getDescription())
            .clientSecret(clientSecret)
            .createdAt(payment.getCreatedAt())
            .updatedAt(payment.getUpdatedAt())
            .build();
    }
}
