package com.example.demo.repositories;

import com.example.demo.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    public Optional<Payment> findPaymentByUKassId(String uKassId);
}
