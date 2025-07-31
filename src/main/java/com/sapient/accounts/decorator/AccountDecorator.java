package com.sapient.accounts.decorator;

public class AccountDecorator implements  Account{
    protected Account account;

    public AccountDecorator(Account account) {
        this.account = account;
    }

    @Override
    public String getDetails() {
        return account.getDetails();
    }
}
