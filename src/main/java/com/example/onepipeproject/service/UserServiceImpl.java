package com.example.onepipeproject.service;

import com.example.onepipeproject.Enums.RoleName;
import com.example.onepipeproject.events.UserCreationEvent;
import com.example.onepipeproject.exception.DuplicateException;
import com.example.onepipeproject.exception.OnePipeOPerationNotAllowedException;
import com.example.onepipeproject.exception.OnePipeResourceNotFoundException;
import com.example.onepipeproject.model.Role;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.model.dto.ManagerUpdateRequest;
import com.example.onepipeproject.model.dto.RolesUpdateRequest;
import com.example.onepipeproject.model.dto.SignUpRequest;
import com.example.onepipeproject.model.dto.UserUpdateRequest;
import com.example.onepipeproject.repository.RoleRepository;
import com.example.onepipeproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private ApplicationEventPublisher applicationEventPublisher;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    RoleService roleService;



    @Autowired
    public UserServiceImpl(ApplicationEventPublisher applicationEventPublisher,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           RoleService roleService) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User create(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail()))
            throw new DuplicateException("User Exist");

        User user = new User(signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()),
                signUpRequest.getSalary(),
                signUpRequest.getVacationBalance(),
                signUpRequest.getAnnualBonus());


        user.getRoles().add(roleRepository.findByName(RoleName.ROLE_EMPLOYEE.name()));
        user = userRepository.save(user);
        applicationEventPublisher.publishEvent(new UserCreationEvent(this,user));
        return user;
    }


    @Override
    public User updateUserRoles(RolesUpdateRequest rolesUpdateRequest) {
        boolean userDoesExist = userRepository.existsById(rolesUpdateRequest.getUserId());
        if(!userDoesExist)
            throw new OnePipeResourceNotFoundException("User","id",rolesUpdateRequest.getUserId());
        Set<Long> setOfrolesIdRequest = new HashSet<>();
        setOfrolesIdRequest.addAll(rolesUpdateRequest.getRoles());

//        if(!roleService.getNonExistentRolesIdFromSetOfRolesId(setOfrolesIdRequest).isEmpty())
//            throw new ResourceNotFoundException("Roles","id",setOfrolesIdRequest.toString());

        setOfrolesIdRequest = roleService.getNonExistentRolesIdFromSetOfRolesId(setOfrolesIdRequest);
        if(!setOfrolesIdRequest.isEmpty())
            throw new OnePipeResourceNotFoundException("Roles","id",setOfrolesIdRequest.toString());

        User user = userRepository.findById(rolesUpdateRequest.getUserId());
        List<Role> roleList = roleRepository.findByIdIn(rolesUpdateRequest.getRoles());
        user.getRoles().addAll(roleList);
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUserManager(ManagerUpdateRequest managerUpdateRequest) {
        if(managerUpdateRequest.getManagerUserId() == managerUpdateRequest.getUserId())
            throw new OnePipeOPerationNotAllowedException("Operation Invalid User cannot report to him/her self :(");
        if(!userRepository.existsById(managerUpdateRequest.getUserId()))
            throw new OnePipeResourceNotFoundException("User","id",managerUpdateRequest.getUserId());
        if(!userRepository.existsById(managerUpdateRequest.getManagerUserId()))
            throw new OnePipeResourceNotFoundException("Manager","id",managerUpdateRequest.getManagerUserId());

        User user = userRepository.findById(managerUpdateRequest.getUserId());

        if(user.getManager() != null && user.getManager().getId() == managerUpdateRequest.getManagerUserId())
            throw new OnePipeOPerationNotAllowedException("User Already reports to Manager");

        User manager = userRepository.findById(managerUpdateRequest.getManagerUserId());
        Set<String> stringSet = new HashSet<>();
        manager.getRoles().forEach(role -> stringSet.add(role.getName()));
        if(!stringSet.contains(RoleName.ROLE_MANAGER.name()))
            throw new OnePipeOPerationNotAllowedException("User Manager_Role Is Missing");

        user.setManager(manager);
        userRepository.save(user);
        return user;

    }

    @Override
    public User getUserById(long id) {
        User user = userRepository.findById(id);
        if(user != null)
            return user;

        throw new OnePipeResourceNotFoundException("User","id",id);

    }

    public List<User> getAllEmployee(long managerId) {
        List<User> users = userRepository.findByManager_Id(managerId);
        if(users != null)
            return users;

        throw new OnePipeOPerationNotAllowedException("No Employee found");

    }

    @Override
    public User update(UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userUpdateRequest.getUserId());
        if(user == null)
            throw new OnePipeResourceNotFoundException("User","id",userUpdateRequest.getUserId());
        user.setEmail(userUpdateRequest.getEmail());
        user.setLastName(userUpdateRequest.getLastName());
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setAnnualBonus(userUpdateRequest.getAnnualBonus());
        user.setVacationBalance(userUpdateRequest.getVacationBalance());
        user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));

        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if(user != null)
            return user;
        throw new  OnePipeResourceNotFoundException("User","email",email);
    }
}


