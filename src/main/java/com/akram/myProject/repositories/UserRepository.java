package com.akram.myProject.repositories;

import com.akram.myProject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    public User findUserByUserLogin(String userLogin);
    @Query("FROM User u")
    public List<User> findAllUsers();
}
