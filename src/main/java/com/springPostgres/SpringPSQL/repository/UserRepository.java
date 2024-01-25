package com.springPostgres.SpringPSQL.repository;

import com.springPostgres.SpringPSQL.model.Role;
import com.springPostgres.SpringPSQL.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, String>{

    User findByRole(Role role);
    Optional<User> findUserByEmail(String email);
    Optional<User> findByEmail(String email);
}
