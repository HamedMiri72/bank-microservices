package com.hamedtech.accounts.service;

import com.hamedtech.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     *
     * @param customerDto-CustomerDto object
     */
    void createAccount(CustomerDto customerDto);
}
