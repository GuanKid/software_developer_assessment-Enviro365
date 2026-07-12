package com.enviro.assessment.junior.desiregwanzura.service;


import com.enviro.assessment.junior.desiregwanzura.dto.request.WithdrawalRequest;
import com.enviro.assessment.junior.desiregwanzura.dto.response.WithdrawalResponse;

import java.util.List;

public interface WithdrawalService {

    WithdrawalResponse withdraw(WithdrawalRequest request);

    List<WithdrawalResponse> getWithdrawals();

    String exportWithdrawals();
}