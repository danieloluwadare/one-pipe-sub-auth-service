package com.example.onepipeproject.service.authority;

import com.example.onepipeproject.Enums.RoleName;
import com.example.onepipeproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserCanViewTheirPersonalInformationService implements UserAuthorityService {

    @Override
    public boolean containsRoles(RoleName roleName){
        return applicableRoles().contains(roleName.name());
    }

    @Override
    public boolean isLicensed(long authorizedUserId, long userId) {
        return authorizedUserId==userId;
    }

    @Override
    public Set<String> applicableRoles() {
        Set<String> set = new HashSet<>();
        List<String> list = Arrays.asList(
                RoleName.ROLE_EMPLOYEE.name()
        );
        set.addAll(list);
        return set;
    }
}
