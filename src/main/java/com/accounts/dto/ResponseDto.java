package com.accounts.dto;

public class ResponseDto {

    private String statusCode;

    private String statusMsg;

    private AccountsDto accountsDto;

    private ResponseDto(Builder builder) {
        this.statusCode = builder.statusCode;
        this.statusMsg = builder.statusMsg;
        this.accountsDto = builder.accountsDto;
    }

    public static class Builder {

        private String statusCode;

        private String statusMsg;

        private AccountsDto accountsDto;

        public Builder statusCode(String statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder statusMsg(String statusMsg) {
            this.statusMsg = statusMsg;
            return this;
        }

        public Builder statusCode(AccountsDto accountsDto) {
            this.accountsDto = accountsDto;
            return this;
        }

        public ResponseDto build() {
            return new ResponseDto(this);
        }

    }
}
