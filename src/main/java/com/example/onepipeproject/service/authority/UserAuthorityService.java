package com.example.onepipeproject.service.authority;

import com.example.onepipeproject.Enums.RoleName;

import java.util.Set;

public interface UserAuthorityService {
    boolean containsRoles(RoleName roleName);
    boolean isLicensed(long authorizedUserId, long userId);
    Set<String> applicableRoles();
}
