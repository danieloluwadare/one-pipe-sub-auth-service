package com.example.onepipeproject.repository;

import com.example.onepipeproject.model.SalaryHistory;
import com.example.onepipeproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SalaryHistoryRepository extends JpaRepository<SalaryHistory, Long> {

    SalaryHistory findById(long id);


//    Optional<User> findByEmail(String email);
//    User findById(long id);
//    List<User> findByIdIn(List<Long> userIds);
//    boolean existsByEmail(String email);
//    boolean existsByEmailOrUsername(String username, String email);
}
