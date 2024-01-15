package com.springPostgres.SpringPSQL.service;

import com.springPostgres.SpringPSQL.dto.UserRequest;
import com.springPostgres.SpringPSQL.model.User;

public interface UserService {
    User saveUser(UserRequest userRequest);
}
