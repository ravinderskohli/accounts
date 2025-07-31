package com.sapient.accounts.strategy;

import com.sapient.accounts.dto.AccountsDto;
import com.sapient.accounts.entity.Accounts;

@FunctionalInterface
public interface AccountStrategy {
    Accounts processAccount(AccountsDto accountsDto);
}
