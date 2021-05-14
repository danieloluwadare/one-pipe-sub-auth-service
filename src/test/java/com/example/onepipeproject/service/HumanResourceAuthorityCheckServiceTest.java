//package com.example.onepipeproject.service;
//
//import com.example.onepipeproject.Enums.RoleName;
//import com.example.onepipeproject.model.Role;
//import com.example.onepipeproject.model.User;
//import com.example.onepipeproject.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//class HumanResourceAuthorityCheckServiceTest {
//    @Mock
//    UserRepository userRepositoryMock;
//
//    @InjectMocks
//    HumanResourceAuthorityCheckFactoryService humanResourceAuthorityCheckService;
//
//    @Test
//    void testIsEligibleUsingAHrAuthorizedUserIdCheckingAnEmployeeIdShouldReturnTrue(){
//        long authorizedUserId = 2L;
//        long userId = 1L;
//
//        User Hr = new User();
//        Hr.setId(2l);
//        Hr.setFirstName("Hr");
//        Hr.setLastName("test");
//        Hr.setEmail("Hr@gmail.com");
//        Hr.setSalary(new BigDecimal("20000.00"));
//
//        User user = new User();
//        user.setId(1l);
//        user.setFirstName("test");
//        user.setLastName("test");
//        user.setEmail("test@gmail.com");
//        user.setSalary(new BigDecimal("20000.00"));
////        user.setManager(Hr);
//
//
//
//        Mockito.lenient().when(userRepositoryMock.findById(2L))
//                .thenReturn(Hr);
//        Mockito.lenient().when(userRepositoryMock.findById(1L))
//                .thenReturn(user);
//
//        boolean valid =humanResourceAuthorityCheckService.isEligible(authorizedUserId,userId);
//        assertTrue(valid);
//    }
//
//    @Test
//    void testIsEligibleUsingAHrAuthorizedUserIdCheckingAnotherHrUserIdShouldReturnFalse(){
//        long authorizedUserId = 2L;
//        long userId = 1L;
//        Role role = new Role(1L, RoleName.ROLE_HR.name());
//        Set<Role> roleSet = new HashSet<>();
//        roleSet.add(role);
//
//        User Hr = new User();
//        Hr.setId(2l);
//        Hr.setFirstName("Hr");
//        Hr.setLastName("test");
//        Hr.setEmail("Hr@gmail.com");
//        Hr.setSalary(new BigDecimal("20000.00"));
//        Hr.setRoles(roleSet);
//
//        User user = new User();
//        user.setId(1l);
//        user.setFirstName("test");
//        user.setLastName("test");
//        user.setEmail("test@gmail.com");
//        user.setSalary(new BigDecimal("20000.00"));
//        user.setRoles(roleSet);
//
////        user.setManager(Hr);
//
//        Mockito.lenient().when(userRepositoryMock.findById(2L))
//                .thenReturn(Hr);
//        Mockito.lenient().when(userRepositoryMock.findById(1L))
//                .thenReturn(user);
//
//        boolean valid = humanResourceAuthorityCheckService.isEligible(authorizedUserId,userId);
//        assertTrue(valid);
//    }
//
//
//}