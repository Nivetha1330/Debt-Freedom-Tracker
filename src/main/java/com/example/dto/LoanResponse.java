package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanResponse {

    private String loanName;
    private Double principalAmount;
    private Double interestRate;
    private String lenderName;
    private int tenure;

}
