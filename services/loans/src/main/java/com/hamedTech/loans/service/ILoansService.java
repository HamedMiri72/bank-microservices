package com.hamedTech.loans.service;

import com.hamedTech.loans.dto.LoansDto;
import com.hamedTech.loans.dto.ResponseDto;

public interface ILoansService {

    /**
     *
     * @param mobileNumber - mobile number of the customer
     */
    void createLoan(String mobileNumber);

    /**
     *
     * @param mobileNumber - mobile number of the customer
     * @return - loans details
     */
    LoansDto fetchLoan(String mobileNumber);

    /**
     *
     * @param loansDto - loan details
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateLoan(LoansDto loansDto);


    /**
     *
     * @param mobileNumber - mobile number of the customer
     * @return boolean indicating if the delete of loan details is successful or not
     */
    boolean deleteLoan(String mobileNumber);
}
