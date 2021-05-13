package com.example.onepipeproject.service;

import java.util.Set;

public interface RoleService {
    Set<Long> getNonExistentRolesIdFromSetOfRolesId(Set<Long> rolesId);
}
