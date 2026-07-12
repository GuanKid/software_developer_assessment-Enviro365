package com.enviro.assessment.junior.desiregwanzura.controller;

import com.enviro.assessment.junior.desiregwanzura.dto.request.WithdrawalRequest;
import com.enviro.assessment.junior.desiregwanzura.dto.response.WithdrawalResponse;
import com.enviro.assessment.junior.desiregwanzura.service.WithdrawalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST controller responsible for processing withdrawals
 * and retrieving withdrawal history.
 */
@RestController
@RequestMapping("/api/withdrawals")
@Tag(name = "Withdrawals", description = "Withdrawal operations")
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    public WithdrawalController(WithdrawalService withdrawalService) {
        this.withdrawalService = withdrawalService;
    }

    /**
     * Processes a withdrawal request after validating
     * all applicable business rules.
     */
    @Operation(
            summary = "Process a withdrawal",
            description = "Processes a withdrawal if all business rules are satisfied."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Withdrawal successful"),
            @ApiResponse(responseCode = "400", description = "Business rule validation failed"),
            @ApiResponse(responseCode = "404", description = "Investor not found")
    })
    @PostMapping
    public WithdrawalResponse withdraw(
            @Valid @RequestBody WithdrawalRequest request) {

        return withdrawalService.withdraw(request);
    }

    /**
     * Retrieves all recorded withdrawals.
     */
    @Operation(summary = "Get withdrawal history")
    @GetMapping
    public List<WithdrawalResponse> getWithdrawals() {
        return withdrawalService.getWithdrawals();
    }

    /**
     * Exports withdrawal history as a CSV file.
     */
    @Operation(summary = "Export withdrawal history to CSV")
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportWithdrawals() {

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=withdrawals.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(withdrawalService.exportWithdrawals().getBytes());

    }
}



