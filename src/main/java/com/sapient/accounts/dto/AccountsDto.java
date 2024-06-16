package com.sapient.accounts.dto;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AccountsDto {

    private Long accountNumber;

    private String accountType;

    private String branchAddress;
}
