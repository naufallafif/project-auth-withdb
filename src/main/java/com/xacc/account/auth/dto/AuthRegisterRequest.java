package com.xacc.account.auth.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AuthRegisterRequest(
        @NotNull
        @NotBlank(message = "Harus ada isinya untuk username!")
        String username,
        @NotBlank
        @Size(min = 8, max = 20)
        String password,
        @NotBlank
        @Size(min = 8, max = 20)
        String confirmPassword,
        @NotBlank
        @Size(min = 5, max = 50)
        String firstName,
        @Size(max = 100)
        String lastName,
        @Past
        LocalDate birthDate,
        @NotBlank @Email
        String email,
        @NotBlank @Size(min = 10, max = 13) String
        phoneNumber,
        @NotBlank @Size(max = 2000)
        String address,
        @Size(min = 5, max = 5)
        String postalCode,
        @NotNull
        Boolean isMarried,
        @NotNull @Min(1) @Max(5)
        Integer yearsExperience,
        @NotNull @Min(0) @Max(2)
        Integer totalChildren,
        @NotNull
        @DecimalMin(value = "100")
        @DecimalMax(value = "999999")
        @Digits(integer = 12, fraction = 2, message = "Harus angka decimal dengan 2 angka belakang koma")
        BigDecimal estimatedSalary
) {
}
