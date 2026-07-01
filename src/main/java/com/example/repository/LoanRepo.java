package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Loan;

public interface LoanRepo extends JpaRepository<Loan, Long> {

}
