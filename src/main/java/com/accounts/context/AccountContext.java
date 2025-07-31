package com.accounts.context;

import com.accounts.dto.AccountsDto;
import com.accounts.entity.Accounts;
import com.accounts.strategy.AccountStrategy;

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