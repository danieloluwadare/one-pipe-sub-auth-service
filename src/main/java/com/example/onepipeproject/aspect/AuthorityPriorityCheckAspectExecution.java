package com.example.onepipeproject.aspect;

import com.example.onepipeproject.annotations.AuthorityPriorityCheck;
import com.example.onepipeproject.exception.OnePipeOPerationNotAllowedException;
import com.example.onepipeproject.exception.OnePipeResourceNotFoundException;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.repository.UserRepository;
import com.example.onepipeproject.service.RolesAuthorityFactoryService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Aspect
@Configuration
public class AuthorityPriorityCheckAspectExecution {

    UserRepository userRepository;
    RolesAuthorityFactoryService rolesAuthorityFactoryService;

    public AuthorityPriorityCheckAspectExecution(UserRepository userRepository,
                                                 RolesAuthorityFactoryService rolesAuthorityFactoryService) {
        this.userRepository = userRepository;
        this.rolesAuthorityFactoryService = rolesAuthorityFactoryService;
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Around("@annotation(com.example.onepipeproject.annotations.AuthorityPriorityCheck)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] arguments = joinPoint.getArgs();
        MethodSignature signature= (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();
        List<Long> userId = new ArrayList<>();
        Principal principal=null;
        for(int i =0; i<parameters.length;i++){
            Parameter parameter = parameters[i];
            if(parameter.isAnnotationPresent(PathVariable.class)){
                Long variable = (Long) arguments[i];
                userId.add(variable);
            }
            if(parameter.getName().equalsIgnoreCase("principal")){
                principal = (Principal) arguments[i];
            }
        }
        long userIdLong = userId.get(0);
        User user = userRepository.findById(userIdLong) ;
        if(user==null){
            throw new OnePipeResourceNotFoundException("User","id", userId.get(0));
        }

//        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();


        User authorizedUser = userRepository.findByEmail(principal.getName());
        Object result = new ArrayList<>();
        Boolean validation = rolesAuthorityFactoryService
                .validate(authorizedUser.getRoles(), authorizedUser.getId(), userIdLong);
        if(validation){
            result =joinPoint.proceed();
        }else {
            throw new OnePipeOPerationNotAllowedException("UnAuthorized to Perform this operation");
        }

        return result;
    }
}
