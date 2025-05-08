package com.hamedtech.accounts.constants;

public class AccountsConstants {


    private AccountsConstants() {

        //restrict instantiation
    }

    // static final, so no one can change them and use them without instantiating an object of this class(private constructor)

    public static final String SAVINGS="Savings";
    public static final String ADDRESS = "123 Main Street, New York";
    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Account created successfully";
    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";
    public static final String STATUS_500 = "500";
    public static final String MESSAGE_500 = "An error occurred, Please try again or contact customer support";
}
