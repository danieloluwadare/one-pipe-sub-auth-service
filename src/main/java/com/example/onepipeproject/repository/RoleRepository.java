package com.example.onepipeproject.repository;

import com.example.onepipeproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
    List<Role> findByIdIn(List<Long> roleIds);
}
