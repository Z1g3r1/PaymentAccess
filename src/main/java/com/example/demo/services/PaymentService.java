package com.example.demo.services;

import com.example.demo.entities.Payment;
import com.example.demo.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {
    @Value("${ukassa.shopId}")
    private String shopId;
    @Value(value = "${ukassa.secretKey}")
    private String secretKey;
    PaymentRepository paymentRepository;
    private final RestTemplate restTemplate = new RestTemplate();


    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public String createPayment(BigDecimal amount, String payerEmail) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(shopId ,secretKey);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();
        Map<String, Object> amountMap = new HashMap<>();
        amountMap.put("value", amount.toString());
        amountMap.put("currency", "RUB");
        body.put("amount", amountMap);

        Map<String, Object> confirmation = new HashMap<>();
        confirmation.put("type", "redirect");
        confirmation.put("return_url", "https://мой-сайт.onrender.com/payment/result");
        body.put("confirmation", confirmation);

        String accessToken = UUID.randomUUID().toString();
        Map<String, String> metadata = new HashMap<>();
        metadata.put("accessToken", accessToken);
        body.put("metadata", metadata);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://api.yookassa.ru/v3/payments", request, Map.class
        );
        Map<String, Object> mockResponse = new HashMap<>();
        Map<String, Object> mockConfirmation = new HashMap<>();
        mockConfirmation.put("confirmation_url", "https://yookassa.ru/checkout/mock/" + UUID.randomUUID());
        mockResponse.put("confirmation", mockConfirmation);
        mockResponse.put("id", "mock_payment_" + UUID.randomUUID());
        String confirmationUrl = (String) mockConfirmation.get("confirmation_url");

        String uKassId =(String) response.getBody().get("id");
        Payment payment = new Payment(amount, payerEmail, accessToken, uKassId);
        paymentRepository.save(payment);
        return confirmationUrl;
    }
}
