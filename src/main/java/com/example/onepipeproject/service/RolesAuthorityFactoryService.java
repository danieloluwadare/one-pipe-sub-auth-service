package com.example.onepipeproject.service;

import com.example.onepipeproject.model.Role;
import com.example.onepipeproject.service.authority.UserAuthorityService;
import com.example.onepipeproject.service.role.UserRolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RolesAuthorityFactoryService {
    private Map<String, UserRolesService> userRolesServiceMap;

    @Resource(name = "allUserRolesMapService")
    public void setUserRolesServiceMap(Map<String, UserRolesService> userRolesServiceMap) {
        this.userRolesServiceMap = userRolesServiceMap;
    }

    public boolean validate(Set<Role> roles, long authorizedUserId, long userid){

        for(Role role : roles){
            if(!userRolesServiceMap.containsKey(role.getName()))
                continue;
            List<UserAuthorityService> userAuthorityServices =userRolesServiceMap
                    .get(role.getName()).getAllAssociatedAuthorities();
            for (UserAuthorityService userAuthorityService : userAuthorityServices){
                if(userAuthorityService.isLicensed(authorizedUserId, userid))
                    return true;
            }

        }
        return false;
    }
}
