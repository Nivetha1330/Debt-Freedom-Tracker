package com.example.service;

import org.springframework.stereotype.Service;
import com.example.repository.LoanRepo;
import com.example.model.Loan;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepo loanRepo;

    public LoanService(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    public Loan saveLoan(Loan loan) {
        // Logic to save the loan
        return loanRepo.save(loan);
    }

    public List<Loan> getAllLoans() {
        // Logic to retrieve all loans
        return loanRepo.findAll();
    }
}
