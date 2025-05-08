package com.hamedtech.accounts.service.impl;


import com.hamedtech.accounts.dto.CustomerDto;
import com.hamedtech.accounts.repository.AccountsRepository;
import com.hamedtech.accounts.repository.CustomerRepository;
import com.hamedtech.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {


    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerDto-CustomerDto object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {



    }
}
