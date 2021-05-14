package com.example.onepipeproject.service.authority;

import com.example.onepipeproject.Enums.RoleName;
import com.example.onepipeproject.model.User;
import com.example.onepipeproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class AnyManagerCanViewHisSubordinate implements UserAuthorityService {
    private UserRepository userRepository;

    public AnyManagerCanViewHisSubordinate(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean containsRoles(RoleName roleName){
        return applicableRoles().contains(roleName.name());
    }


    @Override
    public boolean isLicensed(long authorizedUserId, long userId) {
        long manager = authorizedUserId;
        User employee = userRepository.findById(userId);
        if(employee.getManager() == null)
            return false;
        return employee.getManager().getId().equals(manager);

    }

    @Override
    public Set<String> applicableRoles() {
        Set<String> set = new HashSet<>();
        List<String> list = Arrays.asList(
                RoleName.ROLE_MANAGER.name(),
                RoleName.ROLE_HR.name()
        );
        set.addAll(list);
        return set;
    }
}
