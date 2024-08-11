package com.sapient.accounts.service.impl;

import com.sapient.accounts.dto.AccountsDto;
import com.sapient.accounts.dto.CustomerDetailsDto;
import com.sapient.accounts.entity.Accounts;
import com.sapient.accounts.exception.CustomerNotExistsException;
import com.sapient.accounts.exception.ResourceNotFoundException;
import com.sapient.accounts.repository.AccountsRepository;
import com.sapient.accounts.service.ICustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.cloud.stream.function.StreamBridge;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountsServiceImplTest {

    @InjectMocks
    private AccountsServiceImpl accountsService;

    @Mock
    private AccountsRepository accountsRepository;
    private AccountsDto accountsDto;
    private CustomerDetailsDto customerDetailsDto;


    @Mock
    private ICustomerService customerService;

    @Mock
    private StreamBridge streamBridge;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createAccount_CustomerNotExists() {
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setCustomerId(1L);

        when(customerService.fetchCustomerDetails(anyLong())).thenReturn(null);

        assertThrows(CustomerNotExistsException.class, () -> accountsService.createAccount(accountsDto));
    }
    @Test
    void testCreateAccount_CustomerExists_AccountSaveFails() {
        // Setup
        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        customerDetailsDto.setName("customer1");

        // Mocking
        when(customerService.fetchCustomerDetails(1L)).thenReturn(customerDetailsDto);
        when(accountsRepository.save(any(Accounts.class))).thenReturn(null);

        // DTO setup
        AccountsDto accountsDto = new AccountsDto();
        accountsDto.setCustomerId(1L);
        accountsDto.setAccountNumber(Optional.of(12333333333l));

        // Execution and Verification
        assertDoesNotThrow(() -> accountsService.createAccount(accountsDto));
        verify(customerService).fetchCustomerDetails(1L); // Corrected to match the mocked input
        verify(accountsRepository).save(any(Accounts.class));
    }

    @Test
    void fetchAccount_AccountNotFound() {
        when(accountsRepository.findByAccountNumber(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> accountsService.fetchAccount(1L));
    }

    @Test
    void fetchAccount_AccountFound() {
        Accounts accounts = new Accounts();
        accounts.setAccountNumber(1L);
        when(accountsRepository.findByAccountNumber(anyLong())).thenReturn(Optional.of(accounts));

        AccountsDto result = accountsService.fetchAccount(1L);

        assertNotNull(result);
        assertEquals(1L, result.getAccountNumber().get());
    }
}