package com.example.UserAuthentication.repository;

import com.example.UserAuthentication.entity.User;
import com.example.UserAuthentication.exception.InvalidCredentialsException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, String> {
    User findUserByEmailAndPassword(String email, String password);
}
