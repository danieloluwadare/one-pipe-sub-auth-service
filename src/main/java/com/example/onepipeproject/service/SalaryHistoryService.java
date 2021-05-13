package com.example.onepipeproject.service;

import com.example.onepipeproject.model.SalaryHistory;

import java.math.BigDecimal;

public interface SalaryHistoryService {
    SalaryHistory create(long userId, BigDecimal salary);
}
