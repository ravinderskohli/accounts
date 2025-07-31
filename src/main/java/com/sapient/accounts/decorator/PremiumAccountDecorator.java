package com.sapient.accounts.decorator;

public class PremiumAccountDecorator extends   AccountDecorator{
    protected Account account;

    public PremiumAccountDecorator(Account account) {
        super(account);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + ", Premium Features Enabled";
    }
}
