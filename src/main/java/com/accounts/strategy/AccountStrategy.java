package com.accounts.strategy;

import com.accounts.dto.AccountsDto;
import com.accounts.entity.Accounts;

@FunctionalInterface
public interface AccountStrategy {
    Accounts processAccount(AccountsDto accountsDto);
}
