package com.example.onepipeproject.service;



import com.example.onepipeproject.model.User;
import com.example.onepipeproject.model.dto.ManagerUpdateRequest;
import com.example.onepipeproject.model.dto.RolesUpdateRequest;
import com.example.onepipeproject.model.dto.SignUpRequest;

import java.util.List;
import java.util.Set;

public interface IUserService {

    User create(SignUpRequest signUpRequest);
    User updateUserRoles(RolesUpdateRequest rolesUpdateRequest);
    User updateUserManager(ManagerUpdateRequest managerUpdateRequest);

//    User create(SignUpRequest signUpRequest, String password);
//    boolean checkEmailExist(User user);
//    User getUserByEmail(String email);
//    User increaseIllegalCount(String email);
//    User resetIllegalCount(String email);
//    User blockUser(User user);
//    User unBlockUser(User user);
//    List<User> getAll();
//    User getOne(Long id);

}
