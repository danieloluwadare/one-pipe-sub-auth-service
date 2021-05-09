package com.example.onepipeproject.repository;

import com.example.onepipeproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);

    Optional<User> findById(String id);

    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByPhoneNo(String phoneNo);

    Boolean existsByEmail(String email);


//    boolean existsByEmailOrUsername(String username, String email);
}
