package com.akram.myProject.controllers;

import com.akram.myProject.entities.User;
import com.akram.myProject.services.UserService;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
