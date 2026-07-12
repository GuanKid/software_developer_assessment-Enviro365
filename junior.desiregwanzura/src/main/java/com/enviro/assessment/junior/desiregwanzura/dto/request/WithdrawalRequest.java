package com.enviro.assessment.junior.desiregwanzura.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class WithdrawalRequest {

    @NotNull(message = "Investor ID is required")
    private Long investorId;

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Amount is required")
    @DecimalMin(value = "0.10", message = "Withdrawal amount must be greater than zero")
    private BigDecimal amount;

    public WithdrawalRequest() {
    }

    public Long getInvestorId() {
        return investorId;
    }

    public void setInvestorId(Long investorId) {
        this.investorId = investorId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}