package com.enviro.assessment.junior.desiregwanzura.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductType productType;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "investment_value")
    private BigDecimal value;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "investor_id")
    private Investor investor;
}
