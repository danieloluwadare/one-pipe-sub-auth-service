package com.example.onepipeproject.service;

import com.example.onepipeproject.model.Role;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.repository.RoleRepository;
import com.example.onepipeproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class RoleServiceImplTest {

    @Mock
    RoleRepository roleRepositoryMock;

    @InjectMocks
    RoleServiceImpl roleService;

    @Test
    public void testGetNonExistentRolesIdFromSetOfRolesIdWhenAllSetOfRolesExistsInDatabaseItShouldReturnEmptySet() {

        List<Long>roleList = Arrays.asList(1l,2l,3l);
        Set<Long> idsOfRolesToSearch = new HashSet<>();
        idsOfRolesToSearch.addAll(roleList);

        Role role1 = new Role(1l,"Employeee");
        Role role2 = new Role(2l,"Employeee");
        Role role3 = new Role(3l,"Employeee");

        List<Role> rolesReturnedFromRepository = new ArrayList<>();
        rolesReturnedFromRepository.add(role1);
        rolesReturnedFromRepository.add(role2);
        rolesReturnedFromRepository.add(role3);

        Mockito.lenient().when(roleRepositoryMock.findByIdIn(Mockito.anyList()))
                .thenReturn(rolesReturnedFromRepository);

        Set<Long> set = roleService.getNonExistentRolesIdFromSetOfRolesId(idsOfRolesToSearch);

        assertEquals(0,set.size());
    }

    @Test
    public void testGetNonExistentRolesIdFromSetOfRolesIdWhenSomeSetOfRolesExistsInDatabaseItShouldReturnSetOfMissingRoles() {

        List<Long>roleList = Arrays.asList(1l,2l,3l,4l,5l);
        Set<Long> idsOfRolesToSearch = new HashSet<>();
        idsOfRolesToSearch.addAll(roleList);

        Role role1 = new Role(1l,"Employeee");
        Role role2 = new Role(2l,"Employeee");
        Role role3 = new Role(3l,"Employeee");

        List<Role> rolesReturnedFromRepository = new ArrayList<>();
        rolesReturnedFromRepository.add(role1);
        rolesReturnedFromRepository.add(role2);
        rolesReturnedFromRepository.add(role3);

        Mockito.lenient().when(roleRepositoryMock.findByIdIn(Mockito.anyList()))
                .thenReturn(rolesReturnedFromRepository);

        Set<Long> set = roleService.getNonExistentRolesIdFromSetOfRolesId(idsOfRolesToSearch);

        assertEquals(2,set.size());
    }
}