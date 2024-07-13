package com.sapient.accounts.service;

import com.sapient.accounts.dto.AccountsDto;
import com.sapient.accounts.dto.CustomerDetailsDto;

public interface IAccountsService {

    /**
     *
     * @param accountsDto - AccountsDto
     */
    void createAccount(AccountsDto accountsDto);
    AccountsDto fetchAccount(Long accountNumber);


/*
    CustomerDetailsDto fetchAccount(String mobileNumber);

    boolean updateAccount(AccountsDto customerDto);

    boolean deleteAccount(String mobileNumber);
*/

}
