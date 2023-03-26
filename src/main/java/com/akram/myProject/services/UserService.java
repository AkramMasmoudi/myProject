package com.akram.myProject.services;


import com.akram.myProject.entities.User;
import com.akram.myProject.objects.UserVO;

import java.util.List;

public interface UserService {
    public void saveUser(User user);
    public User findAuthenticatedUser();
    public List<UserVO> findAllUsers();
}
