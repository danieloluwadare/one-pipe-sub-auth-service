package com.example.onepipeproject.controller;

import com.example.onepipeproject.annotations.AuthorityPriorityCheck;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.model.dto.ManagerUpdateRequest;
import com.example.onepipeproject.model.dto.RolesUpdateRequest;
import com.example.onepipeproject.model.dto.SignUpRequest;
import com.example.onepipeproject.model.dto.UserUpdateRequest;
import com.example.onepipeproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(path = "/create")
    public User create(@RequestBody @Validated SignUpRequest signUpRequest,
                       Principal principal,
                       HttpServletRequest httpServletRequest) {
        logger.info(principal.getName());
        logger.info(httpServletRequest.getUserPrincipal().getName());

        return userService.create(signUpRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping(path = "/update-roles")
    public User updateRoles(@RequestBody @Validated RolesUpdateRequest rolesUpdateRequest,
                       Principal principal,
                       HttpServletRequest httpServletRequest) {
//        logger.info(principal.getName());
//        logger.info(httpServletRequest.getUserPrincipal().getName());

        return userService.updateUserRoles(rolesUpdateRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping(path = "/update-user-manager")
    public User updateUserManager(@RequestBody @Validated ManagerUpdateRequest managerUpdateRequest,
                            Principal principal,
                            HttpServletRequest httpServletRequest) {

        return userService.updateUserManager(managerUpdateRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_MANAGER')")
    @PutMapping(path = "/update-user-basic-information")
    public User updateUserBasicInformation(@RequestBody @Validated UserUpdateRequest userUpdateRequest,
                                  Principal principal,
                                  HttpServletRequest httpServletRequest) {

        return userService.update(userUpdateRequest);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER','ROLE_HR','ROLE_EMPLOYEE')")
    @AuthorityPriorityCheck
    @GetMapping(path = "/view/{id}", produces = APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable long id,Principal principal, HttpServletRequest httpServletRequest) {
        return userService.getUserById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER')")
    @GetMapping(path = "/view-all-employee", produces = APPLICATION_JSON_VALUE)
    public List<User> getAllEmployee(Principal principal, HttpServletRequest httpServletRequest) {
        User manager = userService.getUserByEmail(principal.getName());
        return userService.getAllEmployee(manager.getId());
    }
}
