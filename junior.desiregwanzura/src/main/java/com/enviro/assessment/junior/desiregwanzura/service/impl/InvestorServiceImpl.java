package com.enviro.assessment.junior.desiregwanzura.service.impl;

import com.enviro.assessment.junior.desiregwanzura.dto.response.InvestorResponse;
import com.enviro.assessment.junior.desiregwanzura.dto.response.ProductResponse;
import com.enviro.assessment.junior.desiregwanzura.entity.Investor;
import com.enviro.assessment.junior.desiregwanzura.repository.InvestorRepository;
import com.enviro.assessment.junior.desiregwanzura.service.InvestorService;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class InvestorServiceImpl implements InvestorService {

    private final InvestorRepository investorRepository;

    public InvestorServiceImpl(InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    @Override
    public InvestorResponse getInvestor(Long id) {

        Investor investor = investorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Investor not found"));

        return new InvestorResponse(
                investor.getId(),
                investor.getName(),
                investor.getAge(),
                investor.getBalance(),
                investor.getProducts()
                        .stream()
                        .map(product -> new ProductResponse(
                                product.getId(),
                                product.getProductName(),
                                product.getProductType(),
                                product.getValue()
                        ))
                        .collect(Collectors.toList())
        );
    }
}