package com.example.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDto {

    @NotNull(message = "payment amount cannot be null")
    @Min(value = 50000, message = "payment amount must be at least 50 k")
    @Max(value = 100000, message = "payment amount cannot exceed 1 L")
    private Double amount;
    private String remarks;
}
