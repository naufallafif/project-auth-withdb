package com.xacc.account.auth;

import com.xacc.account.auth.dto.AuthRegisterRequest;
import com.xacc.account.auth.dto.AuthRegisterResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public AuthRegisterResponse toRegisterResponse(AuthRegisterRequest dto) {
        return AuthRegisterResponse.builder()
                .username(dto.username())
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .birthDate(dto.birthDate())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .address(dto.address())
                .postalCode(dto.postalCode())
                .isMarried(dto.isMarried())
                .yearsExperience(dto.yearsExperience())
                .totalChildren(dto.totalChildren())
                .estimatedSalary(dto.estimatedSalary())
                .build();
    }
}