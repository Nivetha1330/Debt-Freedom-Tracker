package com.example.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dto.PartialPaymentRequest;
import com.example.model.Loan;
import com.example.model.Payment;
import com.example.repository.LoanRepo;
import com.example.repository.PaymentRepo;

@Service
public class PaymentService {

    private final PaymentRepo paymentRepo;
    private final LoanRepo loanRepo;

    public PaymentService(PaymentRepo paymentRepo, LoanRepo loanRepo) {
        this.paymentRepo = paymentRepo;
        this.loanRepo = loanRepo;
    }

    public Payment payEMIPayment(Long loanId) {
        Loan loan = getLoanOrThrow(loanId);
        double amount = calculateEmiAmount(loan);
        return createPayment(loan, amount, "EMI payment",loan.getTenure()-1);
    }

    public Payment payPartialPayment(Long loanId, PartialPaymentRequest request) {
        Loan loan = getLoanOrThrow(loanId);
        double minAmount = calculateEmiAmount(loan) * 3;
        double amount = request.getAmount();
        if (amount < minAmount) {
            throw new IllegalArgumentException(String.format("Partial payment must be at least 3 EMI amounts (minimum %.2f)", minAmount));
        }

        double remaining = Optional.ofNullable(loan.getPrincipalAmount()).orElse(0.0);
        if (amount > remaining) {
            throw new IllegalArgumentException(String.format("Partial payment cannot exceed remaining loan amount %.2f", remaining));
        }

        return createPayment(loan, amount, "Partial payment", loan.getTenure() - 3);
    }

    public Payment preCloseLoan(Long loanId) {
        Loan loan = getLoanOrThrow(loanId);
        double amount = Optional.ofNullable(loan.getPrincipalAmount()).orElse(0.0);
        return createPayment(loan, amount, "Preclose payment", 0);
    }

    public List<Payment> getPaymentHistory(Long loanId) {
        return paymentRepo.findByLoanId(loanId);
    }

    private Payment createPayment(Loan loan, double amount, String remarks, int remainingTenure) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Calculated payment amount must be greater than zero");
        }

        Payment payment = Payment.builder()
                .amount(amount)
                .paymentDate(LocalDate.now())
                .remarks(remarks)
                .loan(loan)
                .build();

        Payment savedPayment = paymentRepo.save(payment);

        loan.setPrincipalAmount(Math.max(0.0, Optional.ofNullable(loan.getPrincipalAmount()).orElse(0.0) - amount));
        loan.getPayments().add(savedPayment);
        loan.setTenure(remainingTenure);
        if (remainingTenure == 0) {
            loanRepo.delete(loan);
        } else {
            loanRepo.save(loan);
        }

        return savedPayment;
    }

    private Loan getLoanOrThrow(Long loanId) {
        return loanRepo.findById(loanId)
                .orElseThrow(() -> new IllegalArgumentException("Loan not found: " + loanId));
    }

    private double calculateEmiAmount(Loan loan) {
        double principal = Optional.ofNullable(loan.getPrincipalAmount()).orElse(0.0);
        return Math.max(1000.0, principal / 12.0);
    }

}
