package com.accounts;

import com.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(title = "Account Service REST API Documentation",
		description = "Account Service REST API Documentation", version = "v1", contact = @Contact(name = "Ravinder Singh")))
@EnableConfigurationProperties(value = AccountsContactInfoDto.class)
public class AccountsApplication {

public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
