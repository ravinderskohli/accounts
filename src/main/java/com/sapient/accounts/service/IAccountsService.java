package com.sapient.accounts.service;

import com.sapient.accounts.dto.AccountsDto;
import com.sapient.accounts.dto.CustomerDetailsDto;
import org.springframework.graphql.data.method.annotation.Argument;

public interface IAccountsService {

    /**
     *
     * @param accountsDto - AccountsDto
     */
    AccountsDto createAccount(AccountsDto accountsDto);
    AccountsDto fetchAccount(Long accountNumber);
    AccountsDto accountById(@Argument Long id);

}
