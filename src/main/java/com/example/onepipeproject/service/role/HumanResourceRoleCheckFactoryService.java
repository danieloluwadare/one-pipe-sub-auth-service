package com.example.onepipeproject.service.role;

import com.example.onepipeproject.Enums.RoleName;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.repository.UserRepository;
import com.example.onepipeproject.service.authority.UserAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class HumanResourceRoleCheckFactoryService implements UserRolesService {

    private List<UserAuthorityService> userAuthorityServiceList;

    @Override
    public List<UserAuthorityService> getAllAssociatedAuthorities() {
        return userAuthorityServiceList;
    }

    @Override
    public RoleName getRole() {
        return RoleName.ROLE_HR;
    }


    @Resource(name = "hrRolesAuthoritiesList")
    public void setUserAuthorityServiceList(List<UserAuthorityService> userAuthorityServiceList) {
        this.userAuthorityServiceList = userAuthorityServiceList;
    }
}
