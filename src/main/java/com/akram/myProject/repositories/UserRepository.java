package com.akram.myProject.repositories;

import com.akram.myProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUserLogin(String userLogin);
}
