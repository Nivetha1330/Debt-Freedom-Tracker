package com.example.dto;

import com.example.constants.Lender;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanRequest {

    @NotBlank
    private String loanName;

    @NotNull(message = "principalAmount cannot be null")
    @Min(value = 10000, message = "principalAmount must be at least 10 k")
    @Max(value = 1000000, message = "principalAmount cannot exceed 10 L")
    private Double principalAmount;

    @NotNull(message = "interestRate cannot be null")
    @Min(value = 9, message = "interestRate must be at least 9%")
    @Max(value = 24, message = "interestRate cannot exceed 24%")
    private Double interestRate;

    @NotNull(message="Lender name is required")
    private Lender lenderName;

}
