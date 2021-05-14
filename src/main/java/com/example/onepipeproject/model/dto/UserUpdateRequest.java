package com.example.onepipeproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @NotNull
    private long userId;

    @NotBlank
    @Size(min = 2, max = 40)
    private String firstName;
    @NotBlank
    @Size(min = 4, max = 40)
    private String lastName;

    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private BigDecimal salary;

    @NotNull
    private BigDecimal vacationBalance;

    @NotNull
    private BigDecimal annualBonus;

}
