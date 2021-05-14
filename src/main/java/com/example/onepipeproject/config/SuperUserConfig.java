package com.example.onepipeproject.config;


import com.example.onepipeproject.Enums.RoleName;
import com.example.onepipeproject.model.Role;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.model.dto.RolesUpdateRequest;
import com.example.onepipeproject.model.dto.SignUpRequest;
import com.example.onepipeproject.repository.RoleRepository;
import com.example.onepipeproject.repository.UserRepository;
import com.example.onepipeproject.service.RoleService;
import com.example.onepipeproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class SuperUserConfig implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public void createSuperUser(){
        logger.info("Creating SuperUser");
        User user = userRepository.findByEmail("superuser@gmail.com");
        if(user == null){
            SignUpRequest signUpRequest = new SignUpRequest("superuser","superuser",
                    "superuser@gmail.com","password"
                    ,new BigDecimal("10"),new BigDecimal("10"),new BigDecimal("10"));
            User superuser = userService.create(signUpRequest);

            Role role = roleRepository.findByName(RoleName.ROLE_ADMIN.name());
            List<Long> rolesId = new ArrayList<>();
            rolesId.add(role.getId());
            RolesUpdateRequest rolesUpdateRequest = new RolesUpdateRequest();
            rolesUpdateRequest.setUserId(superuser.getId());
            rolesUpdateRequest.setRoles(rolesId);

            userService.updateUserRoles(rolesUpdateRequest);
            logger.info("SuperUser Created with id = " + superuser.getId());

        }else {
            logger.info("SuperUser Already exists  with id = " + user.getId());

        }



    }

    @Override
    public void run(String... args) throws Exception {
        createSuperUser();

    }
}
