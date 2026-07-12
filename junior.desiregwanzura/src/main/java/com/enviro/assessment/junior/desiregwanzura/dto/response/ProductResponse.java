package com.enviro.assessment.junior.desiregwanzura.dto.response;

import com.enviro.assessment.junior.desiregwanzura.entity.ProductType;

import java.math.BigDecimal;

public class ProductResponse {

    private Long id;
    private String productName;
    private ProductType productType;
    private BigDecimal value;

    public ProductResponse() {
    }

    public ProductResponse(Long id,
                           String productName,
                           ProductType productType,
                           BigDecimal value) {
        this.id = id;
        this.productName = productName;
        this.productType = productType;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}