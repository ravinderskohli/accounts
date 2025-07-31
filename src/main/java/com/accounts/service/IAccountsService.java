package com.accounts.service;

import com.accounts.dto.AccountsDto;
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
