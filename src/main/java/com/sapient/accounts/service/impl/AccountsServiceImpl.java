package com.sapient.accounts.service.impl;

import com.sapient.accounts.constants.AccountsConstants;
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
        CustomerDetailsDto customerDetailsDto = customerService.fetchCustomerDetails(accountsDto.getCustomerId());

        if(Objects.isNull(customerDetailsDto)) {
            throw new CustomerNotExistsException("Customer Not Exist"+ accountsDto.getCustomerId() );
        }
        Accounts accounts = createNewAccount(accountsDto);
        Accounts savedAccount = accountsRepository.save(accounts);
       if(!Objects.nonNull(savedAccount)) {
           sendCommunication(accountsDto, customerDetailsDto);
       }
        accountsDto.setAccountNumber(Optional.of(savedAccount.getAccountNumber()));
       return accountsDto;

    }

    private Accounts createNewAccount(AccountsDto accountsDto) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(accountsDto.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }
    @Override
    public AccountsDto fetchAccount(Long accountNumber) {
        Accounts accounts = accountsRepository.findByAccountNumber(accountNumber).orElseThrow( () ->
                new ResourceNotFoundException("Accounts","accountNumber", accountNumber.toString()));
        AccountsDto accountsDto = AccountsMapper.mapToAccountsDto(accounts, new AccountsDto());
        return  accountsDto;
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

}
