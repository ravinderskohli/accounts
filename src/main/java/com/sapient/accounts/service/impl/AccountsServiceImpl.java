package com.sapient.accounts.service.impl;

import com.sapient.accounts.constants.AccountsConstants;
import com.sapient.accounts.context.AccountContext;
import com.sapient.accounts.decorator.Account;
import com.sapient.accounts.decorator.PremiumAccountDecorator;
import com.sapient.accounts.dto.AccountsDto;
import com.sapient.accounts.dto.AccountsMsgDto;
import com.sapient.accounts.dto.CustomerDetailsDto;
import com.sapient.accounts.entity.Accounts;
import com.sapient.accounts.exception.CustomerNotExistsException;
import com.sapient.accounts.exception.ResourceNotFoundException;
import com.sapient.accounts.mapper.AccountsMapper;
import com.sapient.accounts.repository.AccountsRepository;
import com.sapient.accounts.service.IAccountsService;
import com.sapient.accounts.service.ICustomerService;
import com.sapient.accounts.strategy.AccountStrategy;
import com.sapient.accounts.strategy.CurrentAccountStrategy;
import com.sapient.accounts.strategy.SavingsAccountStrategy;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private static final Logger logger = LoggerFactory.getLogger(AccountsServiceImpl.class);

    private AccountsRepository accountsRepository;
    private ICustomerService customerService;
    private final StreamBridge streamBridge;


    /**
     *
     * @param accountsDto - accountsDTO
     */
    public AccountsDto createAccount(AccountsDto accountsDto) {

        Account premiumAccount = new PremiumAccountDecorator(accountsDto);
        System.out.println(premiumAccount.getDetails());

        CustomerDetailsDto customerDetailsDto = Optional.ofNullable(customerService.fetchCustomerDetails(accountsDto.getCustomerId()))
                .orElseThrow(() -> new CustomerNotExistsException("Customer Not Exist " + accountsDto.getCustomerId()));

        Accounts accounts = processAccount(accountsDto);


        Accounts savedAccount = accountsRepository.save(accounts);
        accountsDto.setAccountNumber(Optional.ofNullable(savedAccount.getAccountNumber()));


       // Optional.ofNullable(savedAccount).ifPresent(account -> sendCommunication(accountsDto, customerDetailsDto));

        return accountsDto;

    }

    @Override
    public AccountsDto fetchAccount(Long accountNumber) {
        Accounts accounts = accountsRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Accounts", "accountNumber", accountNumber.toString()));
        return AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());

    }
    @Override
    public AccountsDto accountById(Long accountNumber) {
        return  fetchAccount(accountNumber);
    }

    public void sendCommunication(AccountsDto accountsDto, CustomerDetailsDto customerDetailsDto) {
        var accountMsgDto = new AccountsMsgDto(accountsDto.getAccountNumber().get()

                ,
                customerDetailsDto.getName(), customerDetailsDto.getEmail(), customerDetailsDto.getMobileNumber());
        logger.info("Sending communication request details {}", accountMsgDto);
        var result = streamBridge.send("sendCommunication-out-0", accountMsgDto);
        logger.info("Is the communication request successfully send to customer {}", result);

    }

    public Accounts processAccount(AccountsDto accountsDto) {
        AccountContext context = new AccountContext();
        AccountStrategy strategy;

        switch (accountsDto.getAccountType().toLowerCase()) {
            case "savings":
                strategy = new SavingsAccountStrategy();
                break;
            case "current":
                strategy = new CurrentAccountStrategy();
                break;
            default:
                throw new IllegalArgumentException("Unknown account type");
        }

        context.setStrategy(strategy);
        return context.executeStrategy(accountsDto);
    }

}
