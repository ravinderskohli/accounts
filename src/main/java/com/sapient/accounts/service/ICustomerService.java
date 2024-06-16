package com.sapient.accounts.service;

import com.sapient.accounts.dto.CustomerDetailsDto;
import com.sapient.accounts.dto.CustomerDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
