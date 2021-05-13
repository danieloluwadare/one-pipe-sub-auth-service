package com.example.onepipeproject.controller;

import com.example.onepipeproject.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {

    @PreAuthorize("hasAnyAuthority('ROLE_USER_UPDATE_DRUG','ROLE_USER_UPDATE_DRUG')")
    @GetMapping(path = "/test-my-drug-test", produces = APPLICATION_JSON_VALUE)
    public List<User> getAllDrugs(Principal principal, HttpServletRequest httpServletRequest) {
//        List<Drug> drugs = new ArrayList<>();
//        drugs.add(new Drug(1l,"drug",new BigDecimal("300"),"dd",0));
//        drugs.add(new Drug(2l,"drug",new BigDecimal("300"),"dd",0));
//        drugs.add(new Drug(3l,"drug",new BigDecimal("300"),"dd",0));
//        drugs.add(new Drug(4l,"drug",new BigDecimal("300"),"dd",0));
//
//        String email = httpServletRequest.getUserPrincipal().getName();

//        System.out.println(email);
//        System.out.println(principal.getName());
////        auditTrailService.generate(httpServletRequest, null, LEGALSTATUS.LEGAL.value);
//        return drugs;
        return null;
    }
}
