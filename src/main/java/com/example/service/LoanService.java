package com.example.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.dto.LoanRequest;
import com.example.dto.LoanResponse;
import com.example.model.Loan;
import com.example.repository.LoanRepo;


@Service
public class LoanService {

    private final LoanRepo loanRepo;

    public LoanService(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    public LoanResponse saveLoan(LoanRequest loanRequest) {
        Loan loan = mapToLoan(loanRequest);
        Loan savedLoan = loanRepo.save(loan);
        return mapToLoanResponse(savedLoan);
    }

    public List<LoanResponse> getAllLoans() {
        return loanRepo.findAll().stream()
                .map(this::mapToLoanResponse)
                .collect(Collectors.toList());
    }

    public LoanResponse getLoanById(Long id) {
        return loanRepo.findById(id)
                .map(this::mapToLoanResponse)
                .orElse(null);
    }

    public void deleteLoan(Long id) {
        loanRepo.deleteById(id);
    }

    private Loan mapToLoan(LoanRequest loanRequest) {
        Loan loan = Loan.builder()
            .loanName(loanRequest.getLoanName())
            .principalAmount(loanRequest.getPrincipalAmount())
            .interestRate(loanRequest.getInterestRate())
            .lenderName(loanRequest.getLenderName().name())
            .tenure(loanRequest.getTenure())
            .build();
        return loan;
    }

    private LoanResponse mapToLoanResponse(Loan loan) {
        return new LoanResponse(
                loan.getLoanName(),
                loan.getPrincipalAmount(),
                loan.getInterestRate(),
                loan.getLenderName(),
                loan.getTenure());
    }

}
