package com.enviro.assessment.junior.desiregwanzura.util;

import com.enviro.assessment.junior.desiregwanzura.entity.Withdrawal;

import java.util.List;

public class CsvExporter {

    public static String export(List<Withdrawal> withdrawals) {

        StringBuilder csv = new StringBuilder();

        csv.append("Date,Investor,Product,Amount,Remaining Balance\n");

        for (Withdrawal withdrawal : withdrawals) {

            csv.append(withdrawal.getWithdrawalDate())
                    .append(",")
                    .append(withdrawal.getInvestor().getName())
                    .append(",")
                    .append(withdrawal.getProduct().getProductName())
                    .append(",")
                    .append(withdrawal.getAmount())
                    .append(",")
                    .append(withdrawal.getRemainingBalance())
                    .append("\n");
        }

        return csv.toString();
    }
}