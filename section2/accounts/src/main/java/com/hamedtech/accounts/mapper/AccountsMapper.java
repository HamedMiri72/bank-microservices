package com.hamedtech.accounts.mapper;

import com.hamedtech.accounts.dto.AccountsDto;
import com.hamedtech.accounts.entity.Accounts;

public class AccountsMapper {

    /**
     * map all the data from accounts to accountsdto
     * @param accounts
     * @param accountsDto
     * @return
     */
    public static AccountsDto  mapToAccountsDto(Accounts accounts, AccountsDto accountsDto){

        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());

        return accountsDto;
    }

    /**
     * map all the data from accountsdto to account
     * @param accountsDto
     * @param accounts
     * @return
     */
    public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts){

        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddress(accountsDto.getBranchAddress());

        return accounts;

    }
}
