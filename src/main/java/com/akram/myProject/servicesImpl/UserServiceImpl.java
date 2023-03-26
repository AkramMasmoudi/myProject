package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.Unit;
import com.akram.myProject.entities.User;
import com.akram.myProject.objects.UnitVO;
import com.akram.myProject.objects.UserVO;
import com.akram.myProject.repositories.UserRepository;
import com.akram.myProject.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    public static final String INFO_SAVE =  "save user ({}) with password token : ";
    public static final String USER_NOT_FOUND =  "User {} not found in DB :(";
    public static final String USER_FOUND =  "User {} found in DB :)";
    public static final String USER_NULL = "user is null in saveUser Methode";
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public void saveUser(User user) throws AuthenticationException{
        if(user != null){
           user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            log.info(INFO_SAVE+user.getUserPassword(),user.getUserLogin());
            userRepository.save(user);
        }else
            throw new NullPointerException(USER_NULL) ;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserLogin(username);
        if(user == null){
            log.info(USER_NOT_FOUND,username);
            throw new UsernameNotFoundException(USER_NOT_FOUND.replace("{}",username));
        }else{
            log.info(USER_FOUND,username);
        }
        Collection<SimpleGrantedAuthority> authorities =
                Arrays.asList(new SimpleGrantedAuthority( user.getUserRole()));
        return new org.springframework.security.core.userdetails.User( user.getUserLogin(),user.getUserPassword(),
                authorities);
    }
    @Override
    public User findAuthenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userLogin = (String) auth.getPrincipal();
        User user = userRepository.findUserByUserLogin(userLogin);
        return user;
    }
    @Override
    public List<UserVO> findAllUsers(){
        List<User> users = new ArrayList<>();
        try {
            users = userRepository.findAllUsers();
            return users.stream().map(user -> new UserVO(user, LAZY)).collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
