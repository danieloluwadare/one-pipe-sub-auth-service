package com.example.onepipeproject.service;

import com.example.onepipeproject.model.User;
import com.example.onepipeproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ManagerAuthorityCheckServiceTest {
//    @Mock
//    UserRepository userRepositoryMock;
//
//    @InjectMocks
//    ManagerAuthorityCheckFactoryService managerAuthorityCheckService;
//
//    @Test
//    void testIsEligibleUsingAUserIdThatReportsToAManagerIdShouldReturnTrue(){
//        long authorizedUserId = 2L;
//        long userId = 1L;
//
//        User manager = new User();
//        manager.setId(2l);
//        manager.setFirstName("manager");
//        manager.setLastName("test");
//        manager.setEmail("manager@gmail.com");
//        manager.setSalary(new BigDecimal("20000.00"));
//
//        User user = new User();
//        user.setId(1l);
//        user.setFirstName("test");
//        user.setLastName("test");
//        user.setEmail("test@gmail.com");
//        user.setSalary(new BigDecimal("20000.00"));
//        user.setManager(manager);
//
//
//
//        Mockito.lenient().when(userRepositoryMock.findById(2L))
//                .thenReturn(manager);
//        Mockito.lenient().when(userRepositoryMock.findById(1L))
//                .thenReturn(user);
//
//        boolean valid =managerAuthorityCheckService.isEligible(authorizedUserId,userId);
//        assertTrue(valid);
//    }
//
//    @Test
//    void testIsEligibleUsingAUserIdThaDoesNotReportsToAManagerIdShouldReturnFalse(){
//        long authorizedUserId = 2L;
//        long userId = 1L;
//
//        User manager = new User();
//        manager.setId(2l);
//        manager.setFirstName("manager");
//        manager.setLastName("test");
//        manager.setEmail("manager@gmail.com");
//        manager.setSalary(new BigDecimal("20000.00"));
//
//        User user = new User();
//        user.setId(1l);
//        user.setFirstName("test");
//        user.setLastName("test");
//        user.setEmail("test@gmail.com");
//        user.setSalary(new BigDecimal("20000.00"));
////        user.setManager(manager);
//
//
//
//        Mockito.lenient().when(userRepositoryMock.findById(2L))
//                .thenReturn(manager);
//        Mockito.lenient().when(userRepositoryMock.findById(1L))
//                .thenReturn(user);
//
//        boolean valid =managerAuthorityCheckService.isEligible(authorizedUserId,userId);
//        assertFalse(valid);
//    }
//
//    @Test
//    void testIsEligibleUsingAUserIdThatHasNoManagerShouldReturnFalse(){
//        long authorizedUserId = 2L;
//        long userId = 1L;
//
//        User manager = new User();
//        manager.setId(2l);
//        manager.setFirstName("manager");
//        manager.setLastName("test");
//        manager.setEmail("manager@gmail.com");
//        manager.setSalary(new BigDecimal("20000.00"));
//
//        User user = new User();
//        user.setId(1l);
//        user.setFirstName("test");
//        user.setLastName("test");
//        user.setEmail("test@gmail.com");
//        user.setSalary(new BigDecimal("20000.00"));
////        user.setManager(manager);
//
//
//
//        Mockito.lenient().when(userRepositoryMock.findById(2L))
//                .thenReturn(manager);
//        Mockito.lenient().when(userRepositoryMock.findById(1L))
//                .thenReturn(user);
//
//        boolean valid =managerAuthorityCheckService.isEligible(authorizedUserId,userId);
//        assertFalse(valid);
//    }
}