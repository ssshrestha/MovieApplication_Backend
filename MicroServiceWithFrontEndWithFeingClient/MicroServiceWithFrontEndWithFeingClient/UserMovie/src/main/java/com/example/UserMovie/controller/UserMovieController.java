package com.example.UserMovie.controller;

import com.example.UserMovie.entity.Movie;
import com.example.UserMovie.entity.User;
import com.example.UserMovie.exception.UserAlreadyExistsException;
import com.example.UserMovie.service.UserMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class UserMovieController {
    @Autowired
    UserMovieService userMovieService;


    @GetMapping("/")
    public String atHome(){
        return "Inside UserMovie @ReachedHomePage";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println("Inside Register Controller of user");
        return new ResponseEntity<>(userMovieService.registerUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/user/{email}/movie")
    public ResponseEntity<?> saveFavouriteProducts(@RequestBody Movie movie, @PathVariable String email){
        return new ResponseEntity<>(userMovieService.saveUserMovieToList(movie, email), HttpStatus.OK);
    }

//    @GetMapping("/movies")
//    public ResponseEntity<?> showAllMovies()  {
//        try {
//            responseEntity =  new ResponseEntity<>(userMovieService.getAllMovies(), HttpStatus.CREATED);
//        }
//        catch(Exception e)
//        {
//            throw e;
//        }
//        return responseEntity;
//    }
//
//    @PostMapping("/user/movie")
//    public ResponseEntity<?> saveUserMovieToList(@RequestBody Movie movie)  {
//        try {
//            responseEntity = new ResponseEntity<>(userMovieService.saveUserMovieToList(movie), HttpStatus.CREATED);
//        }
//        catch (Exception e)
//        {
//            throw e;
//        }
//        return responseEntity;
//    }
}
