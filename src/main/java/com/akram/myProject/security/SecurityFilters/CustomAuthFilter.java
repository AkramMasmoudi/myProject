package com.akram.myProject.security.SecurityFilters;

import com.akram.myProject.globalVariables.SecurityKeys;
import com.akram.myProject.globalVariables.UserRoles;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.akram.myProject.globalVariables.SecurityKeys.CODE_HMAC256;
import static com.akram.myProject.globalVariables.SecurityKeys.ROLES_KEY;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class CustomAuthFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    public CustomAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String userLogin = request.getParameter("userLogin");
        String userPassword = request.getParameter("userPassword");
        log.info("login : "+userLogin+" password : "+userPassword);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userLogin,userPassword);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth) throws IOException, ServletException {
        log.info("successful Authentication !!");
        User user = (User) auth.getPrincipal();
        Algorithm algorithm = Algorithm.HMAC256(CODE_HMAC256.getBytes());
        List<String> lstRoles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        int expiresDateCoefficient = lstRoles.contains(UserRoles.ADMIN) ? 1 : 10;
        String accessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiresDateCoefficient * 60 * 60 * 1000))
                .withClaim(ROLES_KEY,lstRoles)
                .sign(algorithm);
        String refreshToken = JWT.create()
                .withSubject(user.getUsername())
                .sign(algorithm);

        Map<String,String> tokens = new HashMap<>();
        tokens.put(ACCESS_TOKEN,accessToken);
        tokens.put(REFRESH_TOKEN,refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(),tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("unsuccessful Authentication !!");
        super.unsuccessfulAuthentication(request, response, failed);
    }
}
