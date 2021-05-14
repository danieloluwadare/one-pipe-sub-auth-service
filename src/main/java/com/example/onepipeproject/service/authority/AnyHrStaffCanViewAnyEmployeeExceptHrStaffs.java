package com.example.onepipeproject.service.authority;

import com.example.onepipeproject.Enums.RoleName;
import com.example.onepipeproject.model.Role;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.repository.RoleRepository;
import com.example.onepipeproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
public class AnyHrStaffCanViewAnyEmployeeExceptHrStaffs implements UserAuthorityService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    public AnyHrStaffCanViewAnyEmployeeExceptHrStaffs(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean containsRoles(RoleName roleName){
        return applicableRoles().contains(roleName.name());
    }

    @Override
    public boolean isLicensed(long authorizedUserId, long userId) {
        User user =userRepository.findById(userId);
        Role role = roleRepository.findByName(RoleName.ROLE_HR.name());
        return !user.getRoles().contains(role);
    }

    @Override
    public Set<String> applicableRoles() {
        Set<String> set = new HashSet<>();
        List<String> list = Collections.singletonList(
                RoleName.ROLE_HR.name()
        );
        set.addAll(list);
        return set;
    }
}
