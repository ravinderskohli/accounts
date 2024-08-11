package com.sapient.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts")
public record AccountsMsgDto(Long accountNumber,String name, String email, String mobileNumber) {

}
