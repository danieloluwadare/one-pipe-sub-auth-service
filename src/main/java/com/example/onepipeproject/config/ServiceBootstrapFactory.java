package com.example.onepipeproject.config;

import com.example.onepipeproject.Enums.RoleName;
import com.example.onepipeproject.service.authority.UserAuthorityService;
import com.example.onepipeproject.service.role.UserRolesService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ServiceBootstrapFactory {
    @Bean(name = "managerRolesAuthoritiesList")
    public List<UserAuthorityService> getManagerAuthorityService(List<UserAuthorityService> requests){
        List<UserAuthorityService>userAuthorityServices=new ArrayList<>();
        requests.forEach(request->{
            if(request.containsRoles(RoleName.ROLE_MANAGER))
                userAuthorityServices.add(request);
        });
        return userAuthorityServices;
    }

    @Bean(name = "hrRolesAuthoritiesList")
    public List<UserAuthorityService> getHrAuthorityService(List<UserAuthorityService> requests){
        List<UserAuthorityService>userAuthorityServices=new ArrayList<>();
        requests.forEach(request->{
            if(request.containsRoles(RoleName.ROLE_HR))
                userAuthorityServices.add(request);
        });
        return userAuthorityServices;
    }

    @Bean(name = "employeeRolesAuthoritiesList")
    public List<UserAuthorityService> getEmployeeAuthorityService(List<UserAuthorityService> requests){
        List<UserAuthorityService>userAuthorityServices=new ArrayList<>();
        requests.forEach(request->{
            if(request.containsRoles(RoleName.ROLE_EMPLOYEE))
                userAuthorityServices.add(request);
        });
        return userAuthorityServices;
    }

    @Bean(name = "allUserRolesMapService")
    public Map<String, UserRolesService> getAllRoles(List<UserRolesService> requests){
        Map<String, UserRolesService> map = new HashMap<>();
        requests.forEach(request->{
            map.put(request.getRole().name(),request);
        });
        return map;
    }
}
