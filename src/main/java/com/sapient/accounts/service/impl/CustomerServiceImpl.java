package com.sapient.accounts.service.impl;

import com.sapient.accounts.constants.AccountsConstants;
import com.sapient.accounts.dto.AccountsDto;
import com.sapient.accounts.dto.CustomerDetailsDto;
import com.sapient.accounts.dto.CustomerDto;
import com.sapient.accounts.dto.LoansDto;
import com.sapient.accounts.entity.Accounts;
import com.sapient.accounts.entity.Customer;
import com.sapient.accounts.exception.CustomerAlreadyExistsException;
import com.sapient.accounts.exception.ResourceNotFoundException;
import com.sapient.accounts.mapper.AccountsMapper;
import com.sapient.accounts.mapper.CustomerMapper;
import com.sapient.accounts.repository.AccountsRepository;
import com.sapient.accounts.repository.CustomerRepository;
import com.sapient.accounts.service.IAccountsService;
import com.sapient.accounts.service.ICustomerService;
import com.sapient.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private LoansFeignClient loansFeignClient;


    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow( () -> new ResourceNotFoundException("Customer","mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow( () -> new ResourceNotFoundException("Accounts","customerId", customer.getCustomerId().toString()));
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer,new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts,new AccountsDto()));
        ResponseEntity<LoansDto> loansDtoResponseEntity= loansFeignClient.fetchLoanDetails(mobileNumber);
        if(loansDtoResponseEntity != null) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }
        return customerDetailsDto;
    }
}
