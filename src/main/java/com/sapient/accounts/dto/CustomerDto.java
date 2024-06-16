package com.sapient.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer", description = "Schema to hold customer and Account Information")
public class CustomerDto {

    @Schema(name = "Customer", example = "Customer1")
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min=5, max = 30, message = "Length should be between 5 and 30")
    private String name;

    @Schema(name = "Customer", example = "ravinder.singh@sapient.com")
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Email address should be valid value")
    private String email;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
