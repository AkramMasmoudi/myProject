package com.akram.myProject.services;


import com.akram.myProject.entities.User;
import com.akram.myProject.globalVariables.UserRoles;
import com.akram.myProject.objects.UserVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    public User saveUser(User user);

    @Transactional
    boolean deleteUser(long id);
    public User findAuthenticatedUser();
    public List<UserVO> findAllUsers();

    @Transactional
    UserVO modifUser(String prevUserLogin,User newUser);
}
