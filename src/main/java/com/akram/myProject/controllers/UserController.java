package com.akram.myProject.controllers;

import com.akram.myProject.entities.User;
import com.akram.myProject.objects.ResponseObject;
import com.akram.myProject.objects.UserVO;
import com.akram.myProject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
   private final UserService userService;
    @PostMapping("/save")
    @CrossOrigin("*")
    public ResponseEntity<ResponseObject<UserVO>> saveUser(@RequestBody User user){
        List<UserVO> users;
        ResponseObject< UserVO> response = new ResponseObject<>();
        try{
            User savedUser = userService.saveUser(user);
            UserVO userVO = new UserVO(savedUser,LAZY);
            users = userService.findAllUsers();
            response.setListData(users);
            response.setSingleData(userVO);
            return new ResponseEntity<>(response, OK);
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping ("/delete/{id}")
    @CrossOrigin("*")
    public ResponseEntity<ResponseObject<UserVO>> deleteUser(@PathVariable long id){
        List<UserVO> users;
        ResponseObject< UserVO> response = new ResponseObject<>();
        try{
            boolean deleted = userService.deleteUser(id);
            if(!deleted){
                return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
            }
            users = userService.findAllUsers();
            response.setListData(users);
            return new ResponseEntity<>(response, OK);
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping ("/modif")
    @CrossOrigin("*")
    public ResponseEntity<ResponseObject<UserVO>> modifUser(@RequestParam(required = true) String prevUserLogin,@RequestParam(required = true) String newUserLogin,@RequestParam(required = true) String newUserRole ){
        User newUser = new User();
        newUser.setUserLogin(newUserLogin);
        newUser.setUserRole(newUserRole);
        List<UserVO> users;
        ResponseObject< UserVO> response = new ResponseObject<>();
        try{
            UserVO userModified = userService.modifUser(prevUserLogin,newUser);
            users = userService.findAllUsers();
            response.setListData(users);
            response.setSingleData(userModified);
            return new ResponseEntity<>(response, OK);
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users")
    @CrossOrigin("*")
    @Transactional
    public ResponseEntity< ResponseObject<UserVO>> findAllUser(){
        List<UserVO> users;
        ResponseObject< UserVO> response = new ResponseObject<>();
        try{
            users = userService.findAllUsers();
            response.setListData(users);
            return new ResponseEntity<>(response, OK);
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/authenticated")
    @CrossOrigin("*")
    public ResponseEntity<ResponseObject< UserVO>> findAuthenticatedUser(){
        UserVO userVO;
        ResponseObject< UserVO> response = new ResponseObject<>();
        try{
            User user = userService.findAuthenticatedUser();
            userVO = new UserVO(user, LAZY);
            response.setSingleData(userVO);
            return new ResponseEntity<>(response, OK);
        }catch (Exception e){
            response.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(response,INTERNAL_SERVER_ERROR);
        }
    }
}
