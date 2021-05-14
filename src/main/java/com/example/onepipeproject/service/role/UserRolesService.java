package com.example.onepipeproject.service.role;

import com.example.onepipeproject.Enums.RoleName;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.service.authority.UserAuthorityService;

import java.util.List;

public interface UserRolesService {
    List<UserAuthorityService> getAllAssociatedAuthorities();
    RoleName getRole();

}
