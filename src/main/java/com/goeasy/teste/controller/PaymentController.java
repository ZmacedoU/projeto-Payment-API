package com.goeasy.teste.controller;

import com.goeasy.teste.dto.PaymentRequest;
import com.goeasy.teste.dto.PaymentResponse;
import com.goeasy.teste.service.PaymentService;
import com.stripe.exception.StripeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Payments", description = "Payment management APIs using Stripe integration")
public class PaymentController {
    
    private final PaymentService paymentService;
    
    /**
     * Cria um novo pagamento
     * POST /api/payments
     */
    @PostMapping
    @Operation(
        summary = "Create a new payment",
        description = "Creates a payment intent in Stripe and saves it in the database with PENDING status"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Payment created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input - validation error"),
        @ApiResponse(responseCode = "502", description = "Stripe API error")
    })
    public ResponseEntity<PaymentResponse> createPayment(@Valid @RequestBody PaymentRequest request) throws StripeException {
        log.info("Received payment request for: {}", request.getCustomerEmail());
        PaymentResponse response = paymentService.createPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * Busca um pagamento por ID
     * GET /api/payments/{id}
     */
    @GetMapping("/{id}")
    @Operation(
        summary = "Get payment by ID",
        description = "Retrieves a specific payment by its database ID"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found")
    })
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long id) {
        log.info("Fetching payment with ID: {}", id);
        PaymentResponse response = paymentService.getPayment(id);
        return ResponseEntity.ok(response);
    }
    
    /**
     * Lista todos os pagamentos
     * GET /api/payments
     */
    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAllPayments(
            @RequestParam(required = false) String customerEmail) {
        
        if (customerEmail != null && !customerEmail.isBlank()) {
            log.info("Fetching payments for customer: {}", customerEmail);
            List<PaymentResponse> payments = paymentService.getPaymentsByCustomer(customerEmail);
            return ResponseEntity.ok(payments);
        }
        
        log.info("Fetching all payments");
        List<PaymentResponse> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }
    
    /**
     * Health check endpoint
     * GET /api/payments/health
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Payment Service is running!");
    }
}
