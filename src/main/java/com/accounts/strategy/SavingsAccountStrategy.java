package com.accounts.strategy;

import com.accounts.constants.AccountsConstants;
import com.accounts.dto.AccountsDto;
import com.accounts.entity.Accounts;

import java.util.Random;

public class SavingsAccountStrategy implements AccountStrategy {
    @Override
    public Accounts processAccount(AccountsDto accountsDto) {
        System.out.println("Processing savings account");
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(accountsDto.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
}
