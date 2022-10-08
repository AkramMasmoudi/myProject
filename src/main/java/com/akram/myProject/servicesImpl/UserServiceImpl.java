package com.akram.myProject.servicesImpl;

import com.akram.myProject.entities.User;
import com.akram.myProject.repositories.UserRepository;
import com.akram.myProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;

public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {

        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserLogin(username);
        if(user == null){
            System.err.println ("User "+username+" not found in DB !");
            throw new UsernameNotFoundException("User "+username+" Not found in DB");
        }else{
            System.out.println("User "+username+" found in DB :)");
        }
        Collection<SimpleGrantedAuthority> authorities =
                Arrays.asList(new SimpleGrantedAuthority( user.getUserRole()));
        return new org.springframework.security.core.userdetails.User( user.getUserLogin(),user.getUserPassword(),
                authorities);
    }
}
