package com.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PartialPaymentRequest {

    @NotNull(message = "Payment amount is required")
    @Min(value = 1, message = "Payment amount must be greater than zero")
    private Double amount;
    private String reducutionType; // "principal" or "interest"
}
