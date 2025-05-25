package com.hamedtech.accounts.service;

import com.hamedtech.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {


    /**
     * Fetches customer details based on a given mobile number.
     *
     * @param mobileNumber the mobile number of the customer for whom details are to be fetched
     * @return a CustomerDetailsDto object containing the customer's personal, account, loan,
     *         and card details
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
