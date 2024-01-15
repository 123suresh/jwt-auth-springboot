package com.springPostgres.SpringPSQL.service;
import com.springPostgres.SpringPSQL.customException.AlreadyExistException;
import com.springPostgres.SpringPSQL.dto.UserRequest;
import com.springPostgres.SpringPSQL.model.User;
import com.springPostgres.SpringPSQL.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public User saveUser(UserRequest userRequest){
        Optional<User> optionalUserRequest = userRepository.findUserByEmail(userRequest.getEmail().toLowerCase());
        if(optionalUserRequest.isPresent()){
              log.error("User already exist with this email :: {} ",userRequest.getEmail());
              throw new AlreadyExistException("User already exist with email "+userRequest.getEmail());
        }
        User user = User.builder()
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
        return userRepository.save(user);
    }
}
