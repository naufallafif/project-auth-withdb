package com.xacc.account.auth.dto;

import lombok.Data;

@Data 
public class AuthTokenRequest {
    private String username;
    private String password;
}
