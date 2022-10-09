package com.akram.myProject.security;

import com.akram.myProject.security.SecurityFilters.CustomAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.akram.myProject.globalVariables.UserRoles.ADMIN;
import static com.akram.myProject.globalVariables.UserRoles.USER;
import static com.akram.myProject.security.SecurityFilters.CustomDSL.customDsl;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity
public class SecurityConfig  {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final String userPattern = "/user/**";
        final String allPattern = "/**";
        http.cors().and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().mvcMatchers(userPattern).hasAuthority(ADMIN);
        http.authorizeRequests().mvcMatchers(POST,allPattern).hasAnyAuthority(USER,ADMIN);
        http.authorizeRequests().mvcMatchers(PUT,allPattern).hasAnyAuthority(USER,ADMIN);
        http.authorizeRequests().mvcMatchers(PATCH,allPattern).hasAnyAuthority(USER,ADMIN);
        http.authorizeRequests().mvcMatchers(DELETE,allPattern).hasAnyAuthority(USER,ADMIN);
        http.authorizeRequests().anyRequest().authenticated();
        http.apply(customDsl());
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
