package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.User;
import com.akram.myProject.repositories.UserRepository;
import com.akram.myProject.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
@Slf4j
@Service

public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Override
    public void saveUser(User user) throws AuthenticationException{
        if(user != null){
           user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
            log.info("save user ("+user.getUserLogin()+") with password token : "+user.getUserPassword());
            userRepository.save(user);
        }else
            throw new NullPointerException("user is null in saveUser Methode") ;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserLogin(username);
        if(user == null){
            log.info("User "+username+" not found in DB !");
            throw new UsernameNotFoundException("User "+username+" Not found in DB");
        }else{
            log.info("User "+username+" found in DB :)");
        }
        Collection<SimpleGrantedAuthority> authorities =
                Arrays.asList(new SimpleGrantedAuthority( user.getUserRole()));
        return new org.springframework.security.core.userdetails.User( user.getUserLogin(),user.getUserPassword(),
                authorities);
    }
}
