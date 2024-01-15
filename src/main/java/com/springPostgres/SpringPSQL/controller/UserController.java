package com.springPostgres.SpringPSQL.controller;

import com.springPostgres.SpringPSQL.dto.UserRequest;
import com.springPostgres.SpringPSQL.model.User;
import com.springPostgres.SpringPSQL.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class UserController {

    private UserService userService;
    public UserController(UserService userService){
        this.userService=userService;
    }
    @GetMapping("/")
    public String hello(){
        return "hello";
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequest));
    }

}
