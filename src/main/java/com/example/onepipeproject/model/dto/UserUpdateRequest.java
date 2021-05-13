package com.example.onepipeproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
    @NotBlank
    private long userId;

    @NotBlank
    @Size(min = 20, max = 40)
    private String firstName;
    @NotBlank
    @Size(min = 4, max = 40)
    private String lastName;

    @Size(max = 100)
    @Email
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private BigDecimal salary;

    @NotBlank
    private BigDecimal vacationBalance;

    @NotBlank
    private BigDecimal annualBonus;

}
