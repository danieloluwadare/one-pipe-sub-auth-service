package com.example.onepipeproject.service;



import com.example.onepipeproject.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
//    User create(SignUpRequest signUpRequest, String password);
//    User create(SignUpRequest signUpRequest);
    boolean checkEmailExist(User user);
    User getUserByEmail(String email);
    User increaseIllegalCount(String email);
    User resetIllegalCount(String email);
    User blockUser(User user);
    User unBlockUser(User user);
    List<User> getAll();
    User getOne(Long id);
    User updateUserRoles(Long id, Set<Long>rolesId);


}
