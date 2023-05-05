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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

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
        final String authPattern = "/user/authenticated";
        final String allPattern = "/**";

        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));

        http.csrf().disable();

        http.cors().configurationSource(request -> corsConfiguration);
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().mvcMatchers(authPattern).authenticated();
        http.authorizeRequests().mvcMatchers(userPattern).hasAuthority(ADMIN);//permitAll()

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
