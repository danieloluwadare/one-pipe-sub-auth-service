package com.example.onepipeproject.repository;

import com.example.onepipeproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findById(long id);
    List<User> findByIdIn(List<Long> userIds);
    List<User> findByManager_Id(long userIds);
    boolean existsByEmail(String email);
//    boolean existsByEmailOrUsername(String username, String email);
}
