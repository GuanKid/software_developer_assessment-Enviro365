package com.enviro.assessment.junior.desiregwanzura.controller;

import com.enviro.assessment.junior.desiregwanzura.dto.response.InvestorResponse;
import com.enviro.assessment.junior.desiregwanzura.service.InvestorService;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for retrieving investor information.
 */
@RestController
@RequestMapping("/api/investors")
@Tag(name = "Investors", description = "Investor portfolio operations")
public class InvestorController {

    private final InvestorService investorService;

    public InvestorController(InvestorService investorService) {
        this.investorService = investorService;
    }

    /**
     * Retrieves an investor and their investment portfolio.
     *
     * @param id investor identifier
     * @return investor details
     */
    @Operation(
            summary = "Get investor by ID",
            description = "Returns an investor together with their investment products."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Investor found"),
            @ApiResponse(responseCode = "404", description = "Investor not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<InvestorResponse> getInvestor(@PathVariable Long id) {
        return ResponseEntity.ok(investorService.getInvestor(id));
    }
}