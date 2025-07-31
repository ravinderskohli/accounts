package com.sapient.accounts.service.client;

import com.sapient.accounts.dto.CustomerDetailsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UsersFallback implements UsersFeignClient {
    @Override
    public ResponseEntity<CustomerDetailsDto> fetchUserDetails(Long customerId) {
        return null;
    }

}
