package com.akram.myProject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration @EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig  {
    private final UserDetailsService userDetails;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        /*http.sessionManagement().sessionCreationPolicy(STATELESS).and()
        http.authorizeRequests().anyRequest().permitAll();
        http.addFilter(null);
                //.antMatchers(UrlMapping.AUTH + UrlMapping.SIGN_UP).permitAll()
                //.antMatchers(UrlMapping.AUTH + UrlMapping.LOGIN).permitAll()
                //.antMatchers(UrlMapping.VALIDATE_JWT).permitAll()
                //.antMatchers("/api/test/**").permitAll()


        //http.addFilterBefore(authenticationJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
*/
        return http.build();
    }
}
