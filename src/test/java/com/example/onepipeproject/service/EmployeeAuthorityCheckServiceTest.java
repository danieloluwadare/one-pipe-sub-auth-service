//package com.example.onepipeproject.service;
//
//import com.example.onepipeproject.repository.RoleRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
////@ExtendWith(MockitoExtension.class)
//class EmployeeAuthorityCheckServiceTest {
//
//    @InjectMocks
//    EmployeeAuthorityCheckService employeeAuthorityCheckService;
//
//    @Test
//    void testIsEligibleUsingTwoDifferentUserIdShouldReturnFalse(){
//        long authorizedUserId = 1L;
//        long userId = 3L;
//
//        boolean valid =employeeAuthorityCheckService.isEligible(authorizedUserId,userId);
//        assertFalse(valid);
//    }
//
//    @Test
//    void testIsEligibleUsingTwoSameUserIdShouldReturnTrue(){
//        long authorizedUserId = 1L;
//        long userId = 1L;
//
//        boolean valid =employeeAuthorityCheckService.isEligible(authorizedUserId,userId);
//        assertTrue(valid);
//    }
//
//}