package com.sapient.accounts.controller;

import com.sapient.accounts.constants.AccountsConstants;
import com.sapient.accounts.dto.AccountsContactInfoDto;
import com.sapient.accounts.dto.AccountsDto;
import com.sapient.accounts.dto.ResponseDto;
import com.sapient.accounts.service.IAccountsService;
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
        iAccountsService.createAccount(accountsDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
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

/*
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDetailsDto> fetchAccountDetails(@RequestParam String mobileNumber) {
        CustomerDetailsDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody AccountsDto accountsDto) {
        boolean isUpdated = iAccountsService.updateAccount(accountsDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }
    @Operation(summary = "create Account REST API",description = "create Account REST API desc")
    @ApiResponses({@ApiResponse(responseCode = "201",description = "Http Status Created"),@ApiResponse(responseCode = "500",
            description = "Http Status Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))})
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
                                                            String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }*/
    @GetMapping("/contact-info")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(accountsContactInfoDto);
    }
}
