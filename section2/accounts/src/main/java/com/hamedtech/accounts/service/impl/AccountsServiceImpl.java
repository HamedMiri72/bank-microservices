package com.hamedtech.accounts.service.impl;


import com.hamedtech.accounts.constants.AccountsConstants;
import com.hamedtech.accounts.dto.AccountsDto;
import com.hamedtech.accounts.dto.CustomerDto;
import com.hamedtech.accounts.entity.Accounts;
import com.hamedtech.accounts.entity.Customer;
import com.hamedtech.accounts.exception.CustomerAlreadyExistsException;
import com.hamedtech.accounts.exception.ResourceNotFoundException;
import com.hamedtech.accounts.mapper.AccountsMapper;
import com.hamedtech.accounts.mapper.CustomerMapper;
import com.hamedtech.accounts.repository.AccountsRepository;
import com.hamedtech.accounts.repository.CustomerRepository;
import com.hamedtech.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {



    private AccountsRepository accountsRepository;


    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());

        Optional<Customer> optionalCustomer =  customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if(optionalCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber"+ customerDto.getMobileNumber());
        }

        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");

        Customer savedCustomer = customerRepository.save(customer);

        accountsRepository.save(createNewAccounts(savedCustomer));
    }



    private Accounts createNewAccounts(Customer customer) {

        Accounts accounts = new Accounts();

        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        accounts.setCustomerId(customer.getCustomerId());
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setCreatedBy("Anonymous");

        Long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccountNumber);

        return accounts;
    }


    @Override
    public CustomerDto fetchAccount(String mobileNumber) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));


        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {

        boolean isUpdated = false;

        AccountsDto accountsDto = customerDto.getAccountsDto();

        if(accountsDto != null){
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString()));

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);


            Long customerId = accounts.getCustomerId();

           Customer customer = customerRepository.findById(customerId)
                   .orElseThrow(()-> new ResourceNotFoundException("Customer", "customerId", customerId.toString()));

           CustomerMapper.mapToCustomer(customerDto, customer);
           customerRepository.save(customer);
           isUpdated = true;
        }

        return isUpdated;


    }

    /**
     *
     * @param mobileNumber- Input mobile number
     * @return boolean indicationg if the delete of account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {

        boolean isDeleted = false;

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Accounts", "mobileNumber", customer.getMobileNumber()));

        accountsRepository.delete(accounts);
        customerRepository.delete(customer);


        isDeleted = true;

        return isDeleted;
    }


}
