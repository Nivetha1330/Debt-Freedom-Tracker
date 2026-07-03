package com.example.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.model.Loan;
import com.example.repository.LoanRepo;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LoanRepo loanRepo;

    public DataInitializer(LoanRepo loanRepo) {
        this.loanRepo = loanRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (loanRepo.count() > 0) {
            return; // already seeded
        }

        List<Loan> loans = Arrays.asList(
            Loan.builder().loanName("Personal Loan").principalAmount(1000000.0).interestRate(10.3).lenderName("ICICI").build(),
            Loan.builder().loanName("Gold Loan").principalAmount(1400000.0).interestRate(9.0).lenderName("HDFC").build()
        );

        loanRepo.saveAll(loans);
        System.out.println("Seeded " + loans.size() + " loan records.");
    }
}
