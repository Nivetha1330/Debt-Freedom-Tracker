package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long>  {

	List<Payment> findByLoanId(Long loanId);

}
