package com.example.UserMovie.service;

import com.example.UserMovie.entity.Movie;
import com.example.UserMovie.entity.User;
import com.example.UserMovie.exception.UserAlreadyExistsException;
import com.example.UserMovie.proxy.IUserProxy;
import com.example.UserMovie.repository.IUserMovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class UserMovieService {
    @Autowired
    IUserMovieRepository iUserMovieRepository;
    @Autowired
    IUserProxy iUserProxy;

    public User registerUser(User user) {
        iUserProxy.saveUser(user);
        System.out.println("Inside UserMovie Service");
        return iUserMovieRepository.save(user);

    }

    public User saveUserMovieToList(Movie movie, String email){
        User user =iUserMovieRepository.findByEmail(email);
        if (user != null){
            if (user.getMovieList() == null){
                user.setMovieList(Arrays.asList(movie));
            }
            else {
                List favMovieList = user.getMovieList();
                favMovieList.add(movie);
                user.setMovieList(favMovieList);
            }
            return iUserMovieRepository.save(user);
        }
        return user;
    }



//    public Movie saveUserMovieToList(Movie movie){
//        return iUserMovieRepository.save(movie);
//    }
//
//    public List<Movie> getAllMovies() {
//        return iUserMovieRepository.findAll();
//    }
}
