package com.goeasy.teste.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentRequest {
    
    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.50", message = "Minimum amount is 0.50")
    @Digits(integer = 8, fraction = 2, message = "Invalid amount format")
    private BigDecimal amount;
    
    @NotBlank(message = "Currency is required")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Currency must be 3 uppercase letters (e.g., USD, BRL)")
    private String currency;
    
    @NotBlank(message = "Customer email is required")
    @Email(message = "Invalid email format")
    private String customerEmail;
    
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}
