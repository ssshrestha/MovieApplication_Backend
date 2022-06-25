package com.example.UserMovie.controller;

import com.example.UserMovie.entity.Movie;
import com.example.UserMovie.entity.User;
import com.example.UserMovie.exception.MovieNotFoundException;
import com.example.UserMovie.exception.UserAlreadyExistsException;
import com.example.UserMovie.exception.UserNotFoundException;
import com.example.UserMovie.repository.IUserMovieRepository;
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

    @Autowired
    IUserMovieRepository iUserMovieRepository;

    private ResponseEntity<?> responseEntity;


    @GetMapping("/")
    public String atHome(){
        return "Inside UserMovie @ReachedHomePage";
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return new ResponseEntity<>(userMovieService.registerUser(user), HttpStatus.CREATED);
    }


    //save movie to particular user
    @PostMapping("/user/movie/{email}")
    public ResponseEntity<?> saveFavouriteProducts(@RequestBody Movie movie, @PathVariable String email){
        return new ResponseEntity<>(userMovieService.saveUserMovieToList(movie, email), HttpStatus.OK);
    }


    @GetMapping("/user/movies/{email}")
    public ResponseEntity<?> getAllUserMoviesFromList(@PathVariable String email) throws UserNotFoundException {
        try{
           responseEntity = new ResponseEntity<>(userMovieService.getAllUserMovies(email), HttpStatus.OK);
        }catch(UserNotFoundException e)
        {
            throw new UserNotFoundException();
        }
        return responseEntity;
    }


    @PutMapping("/user/{email}")
    public ResponseEntity<?> updateUserProductFromList(@PathVariable(value = "email") String email,
                                           @RequestBody User user) throws UserNotFoundException {
        User user1 = iUserMovieRepository.findById(email)
                .orElseThrow(() -> new UserNotFoundException());
        user.setName(user.getName());
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        user.setMovieList(user.getMovieList());

        final User updatedUser = iUserMovieRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }


    @DeleteMapping("/user/{email}/{movieId}")
    public ResponseEntity<?> deleteUserProductFromList(@PathVariable String email,@PathVariable String movieId)
            throws UserNotFoundException, MovieNotFoundException
    {
        try {
            responseEntity = new ResponseEntity<>(userMovieService.deleteUserMovieFromList(email, movieId), HttpStatus.OK);
        } catch (UserNotFoundException | MovieNotFoundException m) {
            throw new MovieNotFoundException();
        }
        return responseEntity;
    }





























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
