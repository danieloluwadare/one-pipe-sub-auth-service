package com.example.onepipeproject.service;

import com.example.onepipeproject.model.SalaryHistory;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.repository.SalaryHistoryRepository;
import com.example.onepipeproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class SalaryHistoryImplTest {

    @Mock
    UserRepository userRepositoryMock;

    @Mock
    SalaryHistoryRepository salaryHistoryRepository;

    @InjectMocks
    SalaryHistoryServiceImpl salaryHistoryService;

    @Test
    public void testCreateSalaryHistory(){

        User user = new User();
        user.setId(1l);
        user.setEmail("test@email.com");
        user.setFirstName("firstname");
        user.setLastName("lastName");
        user.setPassword("password");
        user.setAnnualBonus(new BigDecimal("3000.00"));
        user.setSalary(new BigDecimal("9000.00"));
        user.setVacationBalance(new BigDecimal("9000.00"));


        Mockito.lenient().when(userRepositoryMock.findById(1l))
                .thenReturn(user);

        Mockito.lenient().when(salaryHistoryRepository.save(Mockito.any(SalaryHistory.class)))
                .thenAnswer(i -> i.getArguments()[0]);


        SalaryHistory salaryHistory = salaryHistoryService.create(user.getId(),user.getSalary());

        assertSame(salaryHistory.getUser().getSalary(), user.getSalary());

    }
}