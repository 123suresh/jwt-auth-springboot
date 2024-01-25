package com.springPostgres.SpringPSQL.controller;

import com.springPostgres.SpringPSQL.dto.JwtAuthResponse;
import com.springPostgres.SpringPSQL.dto.RefreshTokenRequest;
import com.springPostgres.SpringPSQL.dto.SigninRequest;
import com.springPostgres.SpringPSQL.dto.UserRequest;
import com.springPostgres.SpringPSQL.model.User;
import com.springPostgres.SpringPSQL.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequest));
    }

    @PostMapping("/signing")
    public ResponseEntity<JwtAuthResponse> signIn(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.refreshToken(refreshTokenRequest));
    }
}
