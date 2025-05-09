package com.hamedtech.accounts.service;

import com.hamedtech.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto-CustomerDto object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param mobileNumber- input mobile number
     * @return Accounts Details based on a given mobile Number
     */
    CustomerDto fetchAccount(String mobileNumber);
}
