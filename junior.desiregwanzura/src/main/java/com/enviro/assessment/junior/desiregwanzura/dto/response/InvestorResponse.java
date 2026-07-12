package com.enviro.assessment.junior.desiregwanzura.dto.response;

import com.enviro.assessment.junior.desiregwanzura.entity.Product;


import java.math.BigDecimal;
import java.util.List;

public class InvestorResponse {

    private Long id;
    private String name;
    private Integer age;
    private BigDecimal balance;
    private List<ProductResponse> products;

    public InvestorResponse() {
    }

    public InvestorResponse(Long id, String name, Integer age, BigDecimal balance, List<ProductResponse> products) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.balance = balance;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}