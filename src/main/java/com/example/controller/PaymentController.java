package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.PartialPaymentRequest;
import com.example.model.Payment;
import com.example.service.PaymentService;


@RestController
@RequestMapping("/api/pay")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/emi/{loanId}")
    public Payment payEMIPayment(@PathVariable Long loanId) {
        return paymentService.payEMIPayment(loanId);
    }

    @PostMapping("/partial/{loanId}")
    public Payment payPartialPayment(@PathVariable Long loanId, @RequestBody PartialPaymentRequest request) {
        return paymentService.payPartialPayment(loanId, request.getAmount());
    }

    @PostMapping("/preclose/{loanId}")
    public Payment preCloseLoan(@PathVariable Long loanId) {
        return paymentService.preCloseLoan(loanId);
    }

    @GetMapping("/history/{loanId}")
    public List<Payment> getPaymentsByLoanId(@PathVariable Long loanId) {
        return paymentService.getPaymentHistory(loanId);
    }
}
