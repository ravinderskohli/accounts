package com.sapient.accounts.facade;

import com.sapient.accounts.dto.AccountsDto;
import com.sapient.accounts.service.IAccountsService;
import com.sapient.accounts.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountCustomerFacade {
    private ICustomerService customerService;

    private final IAccountsService accountsService;

    @Autowired
    public AccountCustomerFacade(IAccountsService accountsService) {
        this.accountsService = accountsService;
    }

    public AccountsDto createAccountAndAddCustomer(AccountsDto accountsDto) {
        return accountsService.createAccount(accountsDto);
    }

}
