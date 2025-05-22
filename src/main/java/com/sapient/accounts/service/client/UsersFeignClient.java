package com.sapient.accounts.service.client;

import com.sapient.accounts.dto.CustomerDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "users", fallback = UsersFallback.class)
public interface UsersFeignClient {

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CustomerDetailsDto> fetchUserDetails(@RequestParam Long customerId);


}
