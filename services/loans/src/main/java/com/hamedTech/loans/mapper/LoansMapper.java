package com.hamedTech.loans.mapper;

import com.hamedTech.loans.dto.LoansDto;
import com.hamedTech.loans.entity.Loans;

public class LoansMapper {

    /**
     * map all the data form loans to loansDto
     * @param loans
     * @param loansDto
     * @return
     */
    public static LoansDto mapToLoansDto(Loans loans, LoansDto loansDto) {

        loansDto.setLoanNumber(loans.getLoanNumber());
        loansDto.setLoanType(loans.getLoanType());
        loansDto.setMobileNumber(loans.getMobileNumber());
        loansDto.setTotalLoan(loans.getTotalLoan());
        loansDto.setAmountPaid(loans.getAmountPaid());
        loansDto.setOutstandingAmount(loans.getOutstandingAmount());

        return loansDto;
    }

    public static Loans mapToLoans(LoansDto loansDto, Loans loans) {

        loans.setLoanNumber(loansDto.getLoanNumber());
        loans.setLoanType(loansDto.getLoanType());
        loans.setMobileNumber(loansDto.getMobileNumber());
        loans.setTotalLoan(loansDto.getTotalLoan());
        loans.setAmountPaid(loansDto.getAmountPaid());
        loans.setOutstandingAmount(loansDto.getOutstandingAmount());

        return loans;
    }
}
