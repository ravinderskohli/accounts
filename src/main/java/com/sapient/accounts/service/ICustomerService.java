package com.sapient.accounts.service;

import com.sapient.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {

    CustomerDetailsDto fetchCustomerDetails(Long customerId);

}
