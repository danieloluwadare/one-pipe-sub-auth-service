package com.example.onepipeproject.service;

import com.example.onepipeproject.model.SalaryHistory;

import java.math.BigDecimal;
import java.util.List;

public interface SalaryHistoryService {
    SalaryHistory create(long userId, BigDecimal salary);
    List<SalaryHistory> getHistory(long userId);
}
