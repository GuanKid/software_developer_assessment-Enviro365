package com.enviro.assessment.junior.desiregwanzura.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @Transient
    public BigDecimal getBalance() {

        return products.stream()
                .map(Product::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @OneToMany(
            mappedBy = "investor",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Product> products = new ArrayList<>();

    @OneToMany(
            mappedBy = "investor",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Withdrawal> withdrawals = new ArrayList<>();
}

