package com.sapient.accounts.context;

import com.sapient.accounts.dto.AccountsDto;
import com.sapient.accounts.entity.Accounts;
import com.sapient.accounts.strategy.AccountStrategy;

import java.util.Optional;

public class AccountContext {
    private AccountStrategy strategy;

    public void setStrategy(AccountStrategy strategy) {
        this.strategy = strategy;
    }

    public Accounts executeStrategy(AccountsDto accountsDto) {

        return Optional.ofNullable(strategy)
                .map(s -> s.processAccount(accountsDto))
                .orElseThrow(() -> new IllegalStateException("No strategy set"));
    }
}