package com.xacc.account.auth.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record AuthRegisterResponse(
        String username,
        String firstName,
        String lastName,
        LocalDate birthDate,
        String email,
        String phoneNumber,
        String address,
        String postalCode,
        Boolean isMarried,
        Integer yearsExperience,
        Integer totalChildren,
        BigDecimal estimatedSalary
) {
}
