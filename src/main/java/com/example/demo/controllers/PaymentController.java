package com.example.demo.controllers;

import com.example.demo.services.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequestMapping ("/payments")
@Controller
public class PaymentController {
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @GetMapping
    public String getPaymentForm() {
        return "payments";
    }
    @PostMapping
    public String addPayment(@RequestParam BigDecimal amount, @RequestParam String payerEmail) {
        String paymentUrl = paymentService.createPayment(amount, payerEmail);
        return "redirect:/" + paymentUrl;
    }
//    @GetMapping ("/{paymentId}")
//    public String getStatusPayment(@PathVariable Long id, Model model) {
//        return "results"; // этот метод вообще вроде не нужен, потому что там я видел в юкассе можно потом задать куда будут приходить уведомления о статусах, можно наверное просто написать @GetMapping ("/results)
//    }

}
