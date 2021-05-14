package com.example.onepipeproject.service;

import com.example.onepipeproject.Enums.RoleName;
import com.example.onepipeproject.events.UserCreationEvent;
import com.example.onepipeproject.exception.DuplicateException;
import com.example.onepipeproject.exception.OnePipeResourceNotFoundException;
import com.example.onepipeproject.model.Role;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.model.dto.ManagerUpdateRequest;
import com.example.onepipeproject.model.dto.RolesUpdateRequest;
import com.example.onepipeproject.model.dto.SignUpRequest;
import com.example.onepipeproject.model.dto.UserUpdateRequest;
import com.example.onepipeproject.repository.RoleRepository;
import com.example.onepipeproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @Captor
    private ArgumentCaptor<UserCreationEvent> eventArgumentCaptor;

    @Mock
    RoleRepository roleRepositoryMock;

    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    UserRepository userRepositoryMock;

    @Mock
    RoleService roleService;

    @InjectMocks
    UserServiceImpl userService;


    @Test
    public void testCreateUserWithSignUpRequestWithDuplicateEmail() {

        String expectedExceptionMessage = "User Exist";

        Mockito.lenient().when(userRepositoryMock.existsByEmail("duplicateTest@email.com"))
                .thenReturn(true);

        Mockito.lenient().when(passwordEncoder.encode(Mockito.anyString()))
                .thenReturn("password");

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("duplicateTest@email.com");
        signUpRequest.setFirstName("firstname");
        signUpRequest.setLastName("lastName");
        signUpRequest.setPassword("password");

        Exception exception = assertThrows(DuplicateException.class, () -> {
            userService.create(signUpRequest);
        });
        assertTrue(expectedExceptionMessage.contains(exception.getMessage()));
    }

    @Test
    public void testCreateUserWithSignUpRequest() {

        Mockito.lenient().when(userRepositoryMock.save(Mockito.any(User.class)))
        .thenAnswer(i -> i.getArguments()[0]);

        Mockito.lenient().when(userRepositoryMock.existsByEmail(Mockito.anyString()))
                .thenReturn(false);

        Mockito.lenient().when(passwordEncoder.encode(Mockito.anyString()))
                .thenReturn("password");

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("test@email.com");
        signUpRequest.setFirstName("firstname");
        signUpRequest.setLastName("lastName");
        signUpRequest.setPassword("password");

        User user=userService.create(signUpRequest);
        assertEquals(user.getEmail(), signUpRequest.getEmail());
//        assertNotNull(user.getId());
    }

    @Test
    public void testCreateUserShouldPublishEvent() {

        Mockito.lenient().when(userRepositoryMock.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Mockito.lenient().when(userRepositoryMock.existsByEmail(Mockito.anyString()))
                .thenReturn(false);

        Mockito.lenient().when(passwordEncoder.encode(Mockito.anyString()))
                .thenReturn("password");

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("test@email.com");
        signUpRequest.setFirstName("firstname");
        signUpRequest.setLastName("lastName");
        signUpRequest.setPassword("password");
        signUpRequest.setAnnualBonus(new BigDecimal("3000.00"));
        signUpRequest.setSalary(new BigDecimal("9000.00"));
        signUpRequest.setVacationBalance(new BigDecimal("9000.00"));



        User user=userService.create(signUpRequest);

        Mockito.verify(applicationEventPublisher).publishEvent(eventArgumentCaptor.capture());
        assertEquals(user.getEmail(), signUpRequest.getEmail());
        assertEquals(signUpRequest.getFirstName(), eventArgumentCaptor.getValue().getUser().getFirstName());
        assertEquals(eventArgumentCaptor.getValue().getUser().getSalary(), user.getSalary());

//        assertNotNull(user.getId());
    }

    @Test
    public void testCreateUserWithSignUpRequestShouldReturnUserWithDefaultRoleAsEmployee() {

        Mockito.lenient().when(userRepositoryMock.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Mockito.lenient().when(userRepositoryMock.existsByEmail(Mockito.anyString()))
                .thenReturn(false);

        Mockito.lenient().when(passwordEncoder.encode(Mockito.anyString()))
                .thenReturn("password");

        Role role = new Role();
        role.setId(1l);
        role.setName(RoleName.ROLE_EMPLOYEE.name());

        Mockito.lenient().when(roleRepositoryMock.findByName(RoleName.ROLE_EMPLOYEE.name()))
                .thenReturn(role);

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setEmail("test@email.com");
        signUpRequest.setFirstName("firstname");
        signUpRequest.setLastName("lastName");
        signUpRequest.setPassword("password");

        User user = userService.create(signUpRequest);

        assertTrue(user.getRoles().contains(role));
    }

    @Test
    public void testUpdateUserRolesWithValidUserIdAndValidRoleInRoleUpdateRequest() {
        RolesUpdateRequest rolesUpdateRequest = new RolesUpdateRequest();
        rolesUpdateRequest.setUserId(1l);
        rolesUpdateRequest.setRoles(Arrays.asList(1l, 2l ,3l, 4l));

        Mockito.lenient().when(userRepositoryMock.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Mockito.lenient().when(roleService.getNonExistentRolesIdFromSetOfRolesId(Mockito.anySet()))
                .thenReturn(new HashSet<>());

        Mockito.lenient().when(userRepositoryMock.existsById(Mockito.anyLong()))
                .thenReturn(true);

        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));



        Mockito.lenient().when(userRepositoryMock.findById(Mockito.anyLong()))
                .thenReturn(user);

        Role role1 = new Role(1l,"Employeee");
        Role role2 = new Role(2l,"Employeee");
        Role role3 = new Role(3l,"Employeee");
        Role role4 = new Role(4l,"Employeee");

        List<Role> rolesReturnedFromRepository = new ArrayList<>();
        rolesReturnedFromRepository.add(role1);
        rolesReturnedFromRepository.add(role2);
        rolesReturnedFromRepository.add(role3);
        rolesReturnedFromRepository.add(role4);

        Mockito.lenient().when(roleRepositoryMock.findByIdIn(Mockito.anyList()))
                .thenReturn(rolesReturnedFromRepository);
        


        User updatedUser =userService.updateUserRoles(rolesUpdateRequest);
        assertTrue(updatedUser.getRoles().size()==rolesUpdateRequest.getRoles().size());
    }

    @Test
    public void testUpdateUserRolesWithInValidRolesIdInRoleUpdateRequestExpectResourceNotFoundException() {
        RolesUpdateRequest rolesUpdateRequest = new RolesUpdateRequest();
        rolesUpdateRequest.setUserId(1l);
        rolesUpdateRequest.setRoles(Arrays.asList(1l, 2l ,3l, 4l));

        Mockito.lenient().when(userRepositoryMock.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Set<Long> set = new HashSet<>();
        set.add(3l);
        Mockito.lenient().when(roleService.getNonExistentRolesIdFromSetOfRolesId(Mockito.anySet()))
                .thenReturn(set);

        Mockito.lenient().when(userRepositoryMock.existsById(Mockito.anyLong()))
                .thenReturn(true);


        String resourceName="Roles";
        String fieldName = "id";
        String fieldValue = set.toString();
        String expectedExceptionMessage = String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);


        Exception exception = assertThrows(OnePipeResourceNotFoundException.class, () -> {
            userService.updateUserRoles(rolesUpdateRequest);
        });
        assertTrue(expectedExceptionMessage.contains(exception.getMessage()));

    }


    @Test
    public void testUpdateUserRolesWithInValidUserIdInRoleUpdateRequestExpectResourceNotFoundException() {
        RolesUpdateRequest rolesUpdateRequest = new RolesUpdateRequest();
        rolesUpdateRequest.setUserId(1l);
        rolesUpdateRequest.setRoles(Arrays.asList(1l, 2l ,3l, 4l));

        Mockito.lenient().when(userRepositoryMock.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        Mockito.lenient().when(roleService.getNonExistentRolesIdFromSetOfRolesId(Mockito.anySet()))
                .thenReturn(new HashSet<>());

        Mockito.lenient().when(userRepositoryMock.existsById(Mockito.anyLong()))
                .thenReturn(false);

        String resourceName="User";
        String fieldName = "id";
        String fieldValue = "1";
        String expectedExceptionMessage = String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);


        Exception exception = assertThrows(OnePipeResourceNotFoundException.class, () -> {
            userService.updateUserRoles(rolesUpdateRequest);
        });
        assertTrue(expectedExceptionMessage.contains(exception.getMessage()));

    }

    @Test
    public void testUpdateUserManagerWithValidMangerIdValidUserIdWhenUserHasNeverBeenAssignedManager(){
        ManagerUpdateRequest managerUpdateRequest = new ManagerUpdateRequest();
        managerUpdateRequest.setUserId(1l);
        managerUpdateRequest.setManagerUserId(2l);

        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));

        User manager = new User();
        manager.setId(2l);
        manager.setFirstName("manager");
        manager.setLastName("test");
        manager.setEmail("manager@gmail.com");
        manager.setSalary(new BigDecimal("20000.00"));

//        user.setManager(manager);

        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getManagerUserId()))
                .thenReturn(true);

        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getUserId()))
                .thenReturn(true);

        Mockito.lenient().when(userRepositoryMock.findById(managerUpdateRequest.getUserId()))
                .thenReturn(user);

        Mockito.lenient().when(userRepositoryMock.findById(managerUpdateRequest.getManagerUserId()))
                .thenReturn(manager);


        Mockito.lenient().when(userRepositoryMock.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        User user1 = userService.updateUserManager(managerUpdateRequest);

        assertTrue(user1.getManager().getId() == managerUpdateRequest.getManagerUserId());


    }

    @Test
    public void testUpdateUserManagerWithValidMangerIdValidUserIdWhenUserHasBeenAssignedManager(){
        ManagerUpdateRequest managerUpdateRequest = new ManagerUpdateRequest();
        managerUpdateRequest.setUserId(1l);
        managerUpdateRequest.setManagerUserId(3l);

        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));

        User manager = new User();
        manager.setId(2l);
        manager.setFirstName("manager");
        manager.setLastName("test");
        manager.setEmail("manager@gmail.com");
        manager.setSalary(new BigDecimal("20000.00"));

        user.setManager(manager);

        User newManager = new User();
        newManager.setId(3l);
        newManager.setFirstName("manager");
        newManager.setLastName("test");
        newManager.setEmail("manager@gmail.com");
        newManager.setSalary(new BigDecimal("20000.00"));

        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getManagerUserId()))
                .thenReturn(true);

        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getUserId()))
                .thenReturn(true);

        Mockito.lenient().when(userRepositoryMock.findById(managerUpdateRequest.getUserId()))
                .thenReturn(user);

        Mockito.lenient().when(userRepositoryMock.findById(managerUpdateRequest.getManagerUserId()))
                .thenReturn(newManager);


        Mockito.lenient().when(userRepositoryMock.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        User user1 = userService.updateUserManager(managerUpdateRequest);

        assertTrue(user1.getManager().getId() == managerUpdateRequest.getManagerUserId());


    }


    @Test
    public void testUpdateUserManagerWithInValidManagerUserIdShouldThrowResourceNotFoundException(){
        ManagerUpdateRequest managerUpdateRequest = new ManagerUpdateRequest();
        managerUpdateRequest.setUserId(1l);
        managerUpdateRequest.setManagerUserId(2l);

        User manager = new User();
        manager.setId(2l);
        manager.setFirstName("manager");
        manager.setLastName("test");
        manager.setEmail("manager@gmail.com");
        manager.setSalary(new BigDecimal("20000.00"));

        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));
        user.setManager(manager);


        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getManagerUserId()))
                .thenReturn(false);

        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getUserId()))
                .thenReturn(true);

        Mockito.lenient().when(userRepositoryMock.findById(managerUpdateRequest.getUserId()))
                .thenReturn(user);

        Mockito.lenient().when(userRepositoryMock.findById(managerUpdateRequest.getUserId()))
                .thenReturn(user);

        String resourceName="Manager";
        String fieldName = "id";
        String fieldValue = "2";
        String expectedExceptionMessage = String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);


        Exception exception = assertThrows(OnePipeResourceNotFoundException.class, () -> {
            userService.updateUserManager(managerUpdateRequest);
        });

        assertTrue(expectedExceptionMessage.contains(exception.getMessage()));

    }

    @Test
    public void testUpdateUserManagerWithInValidUserIdShouldThrowResourceNotFoundException(){
        ManagerUpdateRequest managerUpdateRequest = new ManagerUpdateRequest();
        managerUpdateRequest.setUserId(1l);
        managerUpdateRequest.setManagerUserId(2l);



        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));

        Mockito.lenient().when(userRepositoryMock.findById(managerUpdateRequest.getUserId()))
                .thenReturn(user);

        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getManagerUserId()))
                .thenReturn(true);


        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getUserId()))
                .thenReturn(false);


        String resourceName="User";
        String fieldName = "id";
        String fieldValue = "1";
        String expectedExceptionMessage = String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);


        Exception exception = assertThrows(OnePipeResourceNotFoundException.class, () -> {
            userService.updateUserManager(managerUpdateRequest);
        });

        assertTrue(expectedExceptionMessage.contains(exception.getMessage()));

    }


    @Test
    public void testUpdateUserManagerWherePreviousManagerIdIsEqualToNewManagerUserIdShouldThrowException(){
        ManagerUpdateRequest managerUpdateRequest = new ManagerUpdateRequest();
        managerUpdateRequest.setUserId(1l);
        managerUpdateRequest.setManagerUserId(2l);

        User manager = new User();
        manager.setId(2l);
        manager.setFirstName("manager");
        manager.setLastName("test");
        manager.setEmail("manager@gmail.com");
        manager.setSalary(new BigDecimal("20000.00"));

        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));
        user.setManager(manager);

        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getManagerUserId()))
                .thenReturn(true);

        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getUserId()))
                .thenReturn(true);

        Mockito.lenient().when(userRepositoryMock.findById(managerUpdateRequest.getUserId()))
                .thenReturn(user);


        String expectedExceptionMessage = String.format("User Already reports to Manager");


        Exception exception = assertThrows(Exception.class, () -> {
            userService.updateUserManager(managerUpdateRequest);
        });

        assertTrue(expectedExceptionMessage.contains(exception.getMessage()));
    }

    @Test
    public void testUpdateUserManagerWhereUserIdIsEqualToNewManagerUserIdShouldThrowException(){
        ManagerUpdateRequest managerUpdateRequest = new ManagerUpdateRequest();
        managerUpdateRequest.setUserId(1l);
        managerUpdateRequest.setManagerUserId(1l);

        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));

        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getManagerUserId()))
                .thenReturn(true);

        Mockito.lenient().when(userRepositoryMock.existsById(managerUpdateRequest.getUserId()))
                .thenReturn(true);

        Mockito.lenient().when(userRepositoryMock.findById(managerUpdateRequest.getUserId()))
                .thenReturn(user);


        String expectedExceptionMessage = String.format("Operation Invalid User cannot report to him/her self :(");


        Exception exception = assertThrows(Exception.class, () -> {
            userService.updateUserManager(managerUpdateRequest);
        });

        assertTrue(expectedExceptionMessage.contains(exception.getMessage()));
    }

    @Test
    public void testGetUserById(){
        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));


        Mockito.lenient().when(userRepositoryMock.findById(1l))
                .thenReturn(user);


        User user1 = userService.getUserById(1l);


        assertEquals((long) user1.getId(), 1L);
    }

    @Test
    public void testGetUserByInValidIdReturnResourceNotFoundException(){

        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));


        Mockito.lenient().when(userRepositoryMock.findById(1l))
                .thenReturn(null);

        String resourceName="User";
        String fieldName = "id";
        String fieldValue = "1";
        String expectedExceptionMessage = String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);


        Exception exception = assertThrows(OnePipeResourceNotFoundException.class, () -> {
            userService.getUserById(1l);
        });

        assertTrue(expectedExceptionMessage.contains(exception.getMessage()));
    }

    @Test
    public void testUpdateUserWithSignUpRequest(){
        User user = new User();
        user.setEmail("test@email.com");
        user.setFirstName("firstname");
        user.setLastName("lastName");
        user.setPassword("password");
        user.setId(1L);

        Mockito.lenient().when(userRepositoryMock.save(Mockito.any(User.class)))
                .thenAnswer(i -> i.getArguments()[0]);

//        Mockito.lenient().when(userRepositoryMock.existsByEmail(Mockito.anyString()))
//                .thenReturn(false);

        Mockito.lenient().when(passwordEncoder.encode(Mockito.anyString()))
                .thenReturn("password");

        Mockito.lenient().when(userRepositoryMock.findById(1L))
                .thenReturn(user);

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setEmail("test@email.com");
        userUpdateRequest.setFirstName("firstname");
        userUpdateRequest.setLastName("lastName");
        userUpdateRequest.setPassword("password");
        userUpdateRequest.setUserId(1L);

        User user1 = userService.update(userUpdateRequest);
        assertEquals(user1.getEmail(), userUpdateRequest.getEmail());
    }

    public void testUpdateUserWithNonExistingUserIdThrowResourceNotFoundException(){
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setEmail("test@email.com");
        userUpdateRequest.setFirstName("firstname");
        userUpdateRequest.setLastName("lastName");
        userUpdateRequest.setPassword("password");
        userUpdateRequest.setUserId(1L);


        Mockito.lenient().when(userRepositoryMock.findById(1l))
                .thenReturn(null);

        String resourceName="User";
        String fieldName = "id";
        String fieldValue = "1";
        String expectedExceptionMessage = String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);


        Exception exception = assertThrows(OnePipeResourceNotFoundException.class, () -> {
            userService.update(userUpdateRequest);
        });

        assertTrue(expectedExceptionMessage.contains(exception.getMessage()));
    }

    @Test
    public void testGetUserByEmail(){
        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));


        Mockito.lenient().when(userRepositoryMock.findByEmail(user.getEmail()))
                .thenReturn(user);


        User user1 = userService.getUserByEmail(user.getEmail());


        assertEquals((long) user1.getId(), 1L);
    }

    @Test
    public void testGetUserByInValidEmailReturnResourceNotFoundException(){

        User user = new User();
        user.setId(1l);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setSalary(new BigDecimal("20000.00"));


        Mockito.lenient().when(userRepositoryMock.findByEmail(user.getEmail()))
                .thenReturn(null);

        String resourceName="User";
        String fieldName = "email";
        String fieldValue = user.getEmail();
        String expectedExceptionMessage = String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue);


        Exception exception = assertThrows(OnePipeResourceNotFoundException.class, () -> {
            userService.getUserByEmail(user.getEmail());
        });

        assertTrue(expectedExceptionMessage.contains(exception.getMessage()));
    }

}