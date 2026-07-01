package com.example.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Loan;
import com.example.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }   

    @PostMapping("/saveLoan")
    public Loan saveLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }

    @GetMapping("/getAllLoans")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }
}
