//package com.example.onepipeproject.config;
//
//import com.dada.medthreat.model.Role;
//import com.dada.medthreat.model.User;
//import com.dada.medthreat.model.dto.SignUpRequest;
//import com.dada.medthreat.service.RoleService;
//import com.dada.medthreat.service.UserService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.HashSet;
//import java.util.List;
//
//@Component
//public class SuperUserConfig implements CommandLineRunner {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());
//
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    RoleService roleService;
//
//    public void createSuperUser(){
//        logger.info("Creating SuperUser");
//        User user = userService.getUserByEmail("superuser@gmail.com");
//        if(user == null){
//            SignUpRequest signUpRequest = new SignUpRequest("superuser","superuser","0000000","superuser@gmail.com","password");
//            User superuser = userService.create(signUpRequest);
//
//            List<Role> roles = roleService.getAllRole();
//            HashSet<Long> rolesId = new HashSet<>();
//            roles.forEach(role -> {
//                rolesId.add(role.getId());
//            });
//
//            userService.updateUserRoles(superuser.getId(),rolesId);
//            logger.info("SuperUser Created with id = " + superuser.getId());
//
//        }else {
//            logger.info("SuperUser Already exists  with id = " + user.getId());
//
//        }
//
//
//
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        createSuperUser();
//
//    }
//}
