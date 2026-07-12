package com.enviro.assessment.junior.desiregwanzura.dto.response;

import com.enviro.assessment.junior.desiregwanzura.entity.ProductType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class WithdrawalResponse {

    private Long id;

    private Long productId;

    private String productName;

    private ProductType productType;

    private BigDecimal amount;

    private BigDecimal remainingBalance;

    private LocalDate withdrawalDate;

    private String message;

    public WithdrawalResponse() {
    }

    public WithdrawalResponse(
            Long id,
            Long productId,
            String productName,
            ProductType productType,
            BigDecimal amount,
            BigDecimal remainingBalance,
            LocalDate withdrawalDate,
            String message) {

        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.amount = amount;
        this.remainingBalance = remainingBalance;
        this.withdrawalDate = withdrawalDate;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRemainingBalance() {
        return remainingBalance;
    }

    public void setRemainingBalance(BigDecimal remainingBalance) {
        this.remainingBalance = remainingBalance;
    }

    public LocalDate getWithdrawalDate() {
        return withdrawalDate;
    }

    public void setWithdrawalDate(LocalDate withdrawalDate) {
        this.withdrawalDate = withdrawalDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}