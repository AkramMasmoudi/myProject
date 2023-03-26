package com.akram.myProject.controllers;

import com.akram.myProject.entities.User;
import com.akram.myProject.objects.UserVO;
import com.akram.myProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
   private final UserService userService;
    @PostMapping("/save")
    @CrossOrigin("*")
    public ResponseEntity<User> saveUser(@RequestBody User user){

        try{
            userService.saveUser(user);
            return new ResponseEntity<User>(user, OK);
        }catch (Exception e){
            return new ResponseEntity<User>(user, UNAUTHORIZED);
        }

    }

    @GetMapping("/users")
    @CrossOrigin("*")
    @Transactional
    public ResponseEntity< List<UserVO>> findAllUser(){
        List<UserVO> users = new ArrayList<>();
        try{
            users = userService.findAllUsers();
            return new ResponseEntity< List<UserVO>>(users, OK);
        }catch (Exception e){
            return new ResponseEntity< List<UserVO>>(users, SERVICE_UNAVAILABLE);
        }

    }
}
