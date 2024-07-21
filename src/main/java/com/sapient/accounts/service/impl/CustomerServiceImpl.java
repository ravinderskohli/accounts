package com.sapient.accounts.service.impl;

import com.sapient.accounts.dto.CustomerDetailsDto;
import com.sapient.accounts.service.ICustomerService;
import com.sapient.accounts.service.client.UsersFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private UsersFeignClient usersFeignClient;


    @Override
    public CustomerDetailsDto fetchCustomerDetails(Long customerId) {
      //  ResponseEntity<LoansDto> loansDto =  loansFeignClient.fetchLoanDetails("123444444444");
          ResponseEntity<CustomerDetailsDto> customerDetailsDto= usersFeignClient.fetchUserDetails(customerId);
        if(customerDetailsDto != null) {
            return customerDetailsDto.getBody();
        }
        return null;
        //UsersFeignClientfetchCustomerDetailsLo;
    }
}
