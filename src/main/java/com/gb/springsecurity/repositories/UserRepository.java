package com.gb.springsecurity.repositories;

import com.gb.springsecurity.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findUserByUsername(String username);
}
