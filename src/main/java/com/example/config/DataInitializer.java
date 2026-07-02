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
            Loan.builder().loanName("Personal Loan").principalAmount(50000.0).interestRate(7.5).lenderName("AXIS").build(),
            Loan.builder().loanName("Home Loan").principalAmount(250000.0).interestRate(3.8).lenderName("HDFC").build(),
            Loan.builder().loanName("Auto Loan").principalAmount(30000.0).interestRate(4.2).lenderName("SBI").build(),
            Loan.builder().loanName("Student Loan").principalAmount(40000.0).interestRate(5.0).lenderName("AXIS").build(),
            Loan.builder().loanName("Payday Loan").principalAmount(80000.0).interestRate(24.0).lenderName("CITY_UNION").build(),
            Loan.builder().loanName("Business Loan").principalAmount(75000.0).interestRate(6.5).lenderName("SBI").build(),
            Loan.builder().loanName("Mortgage").principalAmount(450000.0).interestRate(3.5).lenderName("AXIS").build(),
            Loan.builder().loanName("Debt Consolidation Loan").principalAmount(15000.0).interestRate(6.0).lenderName("HDFC").build(),
            Loan.builder().loanName("Credit Builder Loan").principalAmount(12000.0).interestRate(2.5).lenderName("AXIS").build(),
            Loan.builder().loanName("Agricultural Loan").principalAmount(60000.0).interestRate(5.5).lenderName("SBI").build()
        );

        loanRepo.saveAll(loans);
        System.out.println("Seeded " + loans.size() + " loan records.");
    }
}
