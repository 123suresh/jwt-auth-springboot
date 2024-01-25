package com.springPostgres.SpringPSQL.service;
import com.springPostgres.SpringPSQL.customException.AlreadyExistException;
import com.springPostgres.SpringPSQL.dto.JwtAuthResponse;
import com.springPostgres.SpringPSQL.dto.RefreshTokenRequest;
import com.springPostgres.SpringPSQL.dto.SigninRequest;
import com.springPostgres.SpringPSQL.dto.UserRequest;
import com.springPostgres.SpringPSQL.model.Role;
import com.springPostgres.SpringPSQL.model.User;
import com.springPostgres.SpringPSQL.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JWTService jwtService;

    @Autowired
    public void setJwtService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setJWTService(JWTService jwtService) {
        this.jwtService = jwtService;
    }

//    @Autowired
//    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder){
//        this.userRepository=userRepository;
//        this.passwordEncoder=passwordEncoder;
//    }

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username){
                return userRepository.findByEmail(username)
                        .orElseThrow(()->new UsernameNotFoundException("user not found"));
            }
        };
    }

    @Override
    public User saveUser(UserRequest userRequest){
        Optional<User> optionalUserRequest = userRepository.findUserByEmail(userRequest.getEmail().toLowerCase());
        if(optionalUserRequest.isPresent()){
              log.error("User already exist with this email :: {} ",userRequest.getEmail());
              throw new AlreadyExistException("User already exist with email "+userRequest.getEmail());
        }
//        User user = User.builder()
//                .userName(userRequest.getUserName())
//                .email(userRequest.getEmail())
//                .password(userRequest.getPassword())
//                .build();
//        return userRepository.save(user);
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        //in this api we just create user with role user we will not allow user to create admin user
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public JwtAuthResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
                signinRequest.getPassword()));
        var user = userRepository.findByEmail(signinRequest.getEmail())
                .orElseThrow(()->new IllegalStateException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(jwt);
        jwtAuthResponse.setRefreshToken(refreshToken);
        return jwtAuthResponse;
    }

    public JwtAuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        User user = userRepository.findByEmail(userEmail).orElseThrow();
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
            var jwt = jwtService.generateToken(user);
            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setToken(jwt);
            jwtAuthResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthResponse;
        }
        return null;
    }

    }
