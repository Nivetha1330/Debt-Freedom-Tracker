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
            Loan.builder().loanName("Personal Loan").principalAmount(5000.0).interestRate(7.5).lenderName("Acme Bank").build(),
            Loan.builder().loanName("Home Loan").principalAmount(250000.0).interestRate(3.8).lenderName("HomeTrust").build(),
            Loan.builder().loanName("Auto Loan").principalAmount(30000.0).interestRate(4.2).lenderName("DriveFinance").build(),
            Loan.builder().loanName("Student Loan").principalAmount(40000.0).interestRate(5.0).lenderName("EduFund").build(),
            Loan.builder().loanName("Payday Loan").principalAmount(800.0).interestRate(24.0).lenderName("QuickCash").build(),
            Loan.builder().loanName("Business Loan").principalAmount(75000.0).interestRate(6.5).lenderName("BizLend").build(),
            Loan.builder().loanName("Mortgage").principalAmount(450000.0).interestRate(3.5).lenderName("MortgageCo").build(),
            Loan.builder().loanName("Debt Consolidation Loan").principalAmount(15000.0).interestRate(6.0).lenderName("ConsoliBank").build(),
            Loan.builder().loanName("Credit Builder Loan").principalAmount(1200.0).interestRate(2.5).lenderName("CreditStart").build(),
            Loan.builder().loanName("Agricultural Loan").principalAmount(60000.0).interestRate(5.5).lenderName("AgriFinance").build()
        );

        loanRepo.saveAll(loans);
        System.out.println("Seeded " + loans.size() + " loan records.");
    }
}
