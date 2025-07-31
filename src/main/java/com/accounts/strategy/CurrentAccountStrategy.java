package com.accounts.strategy;

import com.accounts.constants.AccountsConstants;
import com.accounts.dto.AccountsDto;
import com.accounts.entity.Accounts;

import java.util.Random;

public class CurrentAccountStrategy implements AccountStrategy {
    @Override
    public Accounts processAccount(AccountsDto accountsDto) {
        System.out.println("Processing Current account");
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(accountsDto.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.CURRENT);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
