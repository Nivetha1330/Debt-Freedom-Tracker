package com.example.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.LoanRequest;
import com.example.dto.LoanResponse;
import com.example.service.LoanService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/saveLoan")
    @ResponseStatus(HttpStatus.CREATED)
    public LoanResponse saveLoan(@RequestBody @Valid LoanRequest loanRequest) {
        System.out.println("Save Loan started");
        return loanService.saveLoan(loanRequest);
    }

    @GetMapping("/getAllLoans")
    public List<LoanResponse> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/getLoanById/{id}")
    public LoanResponse getLoanById(@PathVariable Long id) {
        LoanResponse existingLoan = loanService.getLoanById(id);
        if (existingLoan == null) {
            throw new NoSuchElementException("Loan with ID " + id + " does not exist");
        }
        return loanService.getLoanById(id);
    }

    @DeleteMapping("/deleteLoan/{id}")
    public void deleteLoan(@PathVariable Long id) {
                LoanResponse existingLoan = loanService.getLoanById(id);
        if (existingLoan == null) {
            throw new NoSuchElementException("Loan with ID " + id + " does not exist");
        }
        loanService.deleteLoan(id);
        System.out.println("Loan with ID " + id + " has been deleted.");
    }

    @PutMapping("/updateLoan/{id}")
    public LoanResponse updateLoan(@PathVariable Long id, @RequestBody @Valid LoanRequest loanRequest) {
        LoanResponse existingLoan = loanService.getLoanById(id);
        if (existingLoan != null) {
            existingLoan = LoanResponse.builder()
                .loanName(loanRequest.getLoanName())
                .principalAmount(loanRequest.getPrincipalAmount())
                .interestRate(loanRequest.getInterestRate())
                .lenderName(loanRequest.getLenderName().name())
                .build();
            return loanService.saveLoan(loanRequest);
        } else {
            throw new NoSuchElementException("Loan with ID " + id + " does not exist");
        }
    }
}
