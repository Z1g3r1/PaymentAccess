package com.example.demo.controllers;

import com.example.demo.entities.Payment;
import com.example.demo.services.PaymentService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Optional;


@Controller
public class PaymentController {
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping ("/payments")
    public String getPaymentForm() {
        return "payments";
    }
    @PostMapping ("/payments")
    public String addPayment(@RequestParam BigDecimal amount, @RequestParam String payerEmail) {
        String paymentUrl = paymentService.createPayment(amount, payerEmail);
        return "redirect:" + paymentUrl;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping ("/payments/all")
    public String getAllPayments(Model model) {
        model.addAttribute("payments", paymentService.getAllPayments());
        return "payments-all";
    }
    @GetMapping ("/payments/result")
    public String paymentResult(@RequestParam ("paymentId") String uKassId, Model model) {
        Payment payment = paymentService.getByUKassId(uKassId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        if (payment.getStatus() == Payment.PaymentStatus.PAID) {
            model.addAttribute("success", true);
            model.addAttribute("message", "Платеж принят!");
            return "result";
        }
        model.addAttribute("success", false);
        model.addAttribute("message", "Платеж не оплачен");
        return "result";
    }
    @GetMapping("/")
    public String home() {
        return "redirect:/payments";
    }
}
