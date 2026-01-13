package com.goeasy.teste.repository;

import com.goeasy.teste.model.Payment;
import com.goeasy.teste.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    Optional<Payment> findByStripePaymentIntentId(String paymentIntentId);
    
    List<Payment> findByCustomerEmail(String customerEmail);
    
    List<Payment> findByStatus(PaymentStatus status);
}
