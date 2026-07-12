package com.enviro.assessment.junior.desiregwanzura.service.impl;

import com.enviro.assessment.junior.desiregwanzura.dto.request.WithdrawalRequest;
import com.enviro.assessment.junior.desiregwanzura.dto.response.WithdrawalResponse;
import com.enviro.assessment.junior.desiregwanzura.entity.Investor;
import com.enviro.assessment.junior.desiregwanzura.entity.Product;
import com.enviro.assessment.junior.desiregwanzura.entity.ProductType;
import com.enviro.assessment.junior.desiregwanzura.entity.Withdrawal;
import com.enviro.assessment.junior.desiregwanzura.exception.WithdrawalException;
import com.enviro.assessment.junior.desiregwanzura.repository.InvestorRepository;
import com.enviro.assessment.junior.desiregwanzura.repository.ProductRepository;
import com.enviro.assessment.junior.desiregwanzura.repository.WithdrawalRepository;
import com.enviro.assessment.junior.desiregwanzura.service.WithdrawalService;
import com.enviro.assessment.junior.desiregwanzura.util.CsvExporter;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class WithdrawalServiceImpl implements WithdrawalService {

    private final InvestorRepository investorRepository;
    private final WithdrawalRepository withdrawalRepository;
    private final ProductRepository productRepository;

    public WithdrawalServiceImpl(
            InvestorRepository investorRepository,
            WithdrawalRepository withdrawalRepository,
            ProductRepository productRepository) {

        this.investorRepository = investorRepository;
        this.withdrawalRepository = withdrawalRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<WithdrawalResponse> getWithdrawals() {

        return withdrawalRepository.findAll()
                .stream()
                .map(withdrawal -> new WithdrawalResponse(
                        withdrawal.getId(),
                        withdrawal.getProduct().getId(),
                        withdrawal.getProduct().getProductName(),
                        withdrawal.getProduct().getProductType(),
                        withdrawal.getAmount(),
                        withdrawal.getRemainingBalance(),
                        withdrawal.getWithdrawalDate(),
                        "Completed"
                ))
                .toList();
    }

    @Override
    public WithdrawalResponse withdraw(WithdrawalRequest request) {

        Investor investor = investorRepository.findById(request.getInvestorId())
                .orElseThrow(() -> new WithdrawalException("Investor not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new WithdrawalException("Investment product not found"));

        if (!product.getInvestor().getId().equals(investor.getId())) {
            throw new WithdrawalException(
                    "Selected product does not belong to the investor."
            );
        }

        validateWithdrawal(investor, product, request.getAmount());

        BigDecimal remainingBalance =
                investor.getBalance().subtract(request.getAmount());

        BigDecimal remainingProductValue =
                product.getValue().subtract(request.getAmount());

        product.setValue(remainingProductValue);

        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setInvestor(investor);
        withdrawal.setProduct(product);
        withdrawal.setAmount(request.getAmount());
        withdrawal.setRemainingBalance(remainingBalance);
        withdrawal.setWithdrawalDate(LocalDate.now());

        withdrawalRepository.save(withdrawal);
        productRepository.save(product);
        investorRepository.save(investor);

        return new WithdrawalResponse(
                withdrawal.getId(),
                product.getId(),
                product.getProductName(),
                product.getProductType(),
                withdrawal.getAmount(),
                withdrawal.getRemainingBalance(),
                withdrawal.getWithdrawalDate(),
                "Withdrawal processed successfully."
        );
    }

    private void validateWithdrawal(
            Investor investor,
            Product product,
            BigDecimal amount) {

        if (product.getProductType() == ProductType.RETIREMENT_ANNUITY
                && investor.getAge() <= 65) {

            throw new WithdrawalException(
                    "Investors must be older than 65 years to withdraw from a Retirement Annuity."
            );
        }

        if (amount.compareTo(product.getValue()) > 0) {
            throw new WithdrawalException(
                    "Withdrawal amount exceeds the available investment value."
            );
        }

        BigDecimal ninetyPercent =
                product.getValue().multiply(new BigDecimal("0.90"));

        if (amount.compareTo(ninetyPercent) > 0) {
            throw new WithdrawalException(
                    "Withdrawal exceeds 90% of the selected investment value."
            );
        }
    }

    @Override
    public String exportWithdrawals() {
        return CsvExporter.export(withdrawalRepository.findAll());
    }
}
