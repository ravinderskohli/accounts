package com.accounts.controller;

import com.accounts.constants.AccountsConstants;
import com.accounts.dto.AccountsContactInfoDto;
import com.accounts.dto.AccountsDto;
import com.accounts.dto.ResponseDto;
import com.accounts.facade.AccountCustomerFacade;
import com.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "CRUD REST API for Accounts")
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountsController {
    private final IAccountsService iAccountsService;
    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;


    public AccountsController(IAccountsService iAccountsService) {
        this.iAccountsService = iAccountsService;
    }
   @Operation(summary = "create Account REST API",description = "create Account REST API desc")
   @ApiResponse(responseCode = "201",description = "Http Status Created")
   @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid  @RequestBody AccountsDto accountsDto) {
       AccountCustomerFacade accountCustomerFacade = new AccountCustomerFacade(iAccountsService);
       AccountsDto finalAccountsDto = accountCustomerFacade.createAccountAndAddCustomer(accountsDto);
       ResponseDto responseDto = new ResponseDto.Builder().statusCode(AccountsConstants.STATUS_201)
               .statusMsg(AccountsConstants.MESSAGE_201).statusCode( finalAccountsDto).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/fetch")
    public ResponseEntity<AccountsDto> fetchAccountDetails(@RequestParam Long accountNumber) {
        AccountsDto accountsDto = iAccountsService.fetchAccount(accountNumber);
        return ResponseEntity.status(HttpStatus.OK).body(accountsDto);
    }
    @QueryMapping
    public AccountsDto accountById(@Argument Long id) {
        return iAccountsService.fetchAccount(id);
    }
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }

}
