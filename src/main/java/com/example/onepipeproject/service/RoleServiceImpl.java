package com.example.onepipeproject.service;

import com.example.onepipeproject.model.Role;
import com.example.onepipeproject.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Set<Long> getNonExistentRolesIdFromSetOfRolesId(Set<Long> rolesId) {

        List<Long> listOfRolesToSearch = new ArrayList<>();
        listOfRolesToSearch.addAll(rolesId);
        List<Role> rolesFound = roleRepository.findByIdIn(listOfRolesToSearch);

        rolesFound.stream().forEach(role -> {
            rolesId.remove(role.getId());
        });

        return rolesId;
    }
}
