package com.sapient.accounts.mapper;

import com.sapient.accounts.dto.AccountsDto;
import com.sapient.accounts.entity.Accounts;

import java.util.Optional;

public class AccountsMapper {
    public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(Optional.of(accounts.getAccountNumber()));
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddress(accounts.getBranchAddress());
        return accountsDto;
    }

}
