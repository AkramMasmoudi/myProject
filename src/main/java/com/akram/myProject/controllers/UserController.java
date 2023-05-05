package com.akram.myProject.controllers;

import com.akram.myProject.entities.User;
import com.akram.myProject.objects.ResponseObject;
import com.akram.myProject.objects.UserVO;
import com.akram.myProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
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

    @GetMapping("/authenticated")
    @CrossOrigin("*")
    public ResponseEntity<ResponseObject< UserVO>> findAuthenticatedUser(){
        UserVO userVO = new UserVO();
        ResponseObject< UserVO> response = new ResponseObject<>();
        try{
            User user = userService.findAuthenticatedUser();
            userVO = new UserVO(user, LAZY);
            response.setSingleData(userVO);
            return new ResponseEntity<ResponseObject<UserVO>>(response, OK);
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<ResponseObject<UserVO>>(response,SERVICE_UNAVAILABLE);
        }
    }
}
