package com.example.onepipeproject.service;

import com.example.onepipeproject.exception.OnePipeResourceNotFoundException;
import com.example.onepipeproject.model.SalaryHistory;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.repository.SalaryHistoryRepository;
import com.example.onepipeproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SalaryHistoryServiceImpl implements SalaryHistoryService {
    private UserRepository userRepository;
    private SalaryHistoryRepository salaryHistoryRepository;

    @Autowired
    public SalaryHistoryServiceImpl(UserRepository userRepository, SalaryHistoryRepository salaryHistoryRepository) {
        this.userRepository = userRepository;
        this.salaryHistoryRepository = salaryHistoryRepository;
    }

    @Override
    public SalaryHistory create(long userId, BigDecimal salary) {
        User user = userRepository.findById(userId);
        SalaryHistory salaryHistory = new SalaryHistory(user,salary);
        salaryHistory=salaryHistoryRepository.save(salaryHistory);
        return salaryHistory;
    }

    @Override
    public List<SalaryHistory> getHistory(long userId) {
        User user = userRepository.findById(userId);
        if(user==null){
            throw new OnePipeResourceNotFoundException("Salary History User","id",userId);
        }

        return null;
    }
}
