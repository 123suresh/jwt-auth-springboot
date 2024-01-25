package com.springPostgres.SpringPSQL.service;

import com.springPostgres.SpringPSQL.dto.JwtAuthResponse;
import com.springPostgres.SpringPSQL.dto.RefreshTokenRequest;
import com.springPostgres.SpringPSQL.dto.SigninRequest;
import com.springPostgres.SpringPSQL.dto.UserRequest;
import com.springPostgres.SpringPSQL.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    User saveUser(UserRequest userRequest);
    JwtAuthResponse signin(SigninRequest signinRequest);
    JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
