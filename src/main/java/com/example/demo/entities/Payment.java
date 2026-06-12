package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity @Table (name = "payments")
public class Payment {
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    Long id;
    BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    String payerEmail;
    String accessToken;
    LocalDateTime createdAt;
    String uKassId;

    public Payment() {}

    public Payment(BigDecimal amount, String payerEmail,  String accessToken, String uKassId) {
        this.amount = amount;
        this.payerEmail = payerEmail;
        this.uKassId = uKassId;
        this.status = PaymentStatus.PENDING;
        this.createdAt = LocalDateTime.now();
        this.accessToken = accessToken;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getPayerEmail() {
        return payerEmail;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public enum PaymentStatus {
        PENDING, PAID, CANCELED
    }
}
