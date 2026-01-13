package com.goeasy.teste.dto;

import com.goeasy.teste.model.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {
    
    private Long id;
    private String paymentIntentId;
    private BigDecimal amount;
    private String currency;
    private String customerEmail;
    private PaymentStatus status;
    private String description;
    private String clientSecret; // Usado no frontend para completar o pagamento
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
