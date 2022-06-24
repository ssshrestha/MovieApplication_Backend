package com.example.UserMovie.repository;

import com.example.UserMovie.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IUserMovieRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
