package com.example.UserMovie.service;

import com.example.UserMovie.entity.Movie;
import com.example.UserMovie.entity.User;
import com.example.UserMovie.exception.MovieNotFoundException;
import com.example.UserMovie.exception.UserAlreadyExistsException;
import com.example.UserMovie.exception.UserNotFoundException;
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

    public List<Movie> getAllUserMovies(String email) throws UserNotFoundException {
        if(iUserMovieRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        return iUserMovieRepository.findById(email).get().getMovieList();
    }


    public User deleteUserMovieFromList(String email, int movieId) throws UserNotFoundException, MovieNotFoundException {
        boolean movieIdIsPresent = false;
        if(iUserMovieRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = iUserMovieRepository.findById(email).get();
        List<Movie> movies = user.getMovieList();
        movieIdIsPresent = movies.removeIf(x->x.getMovieId()==(movieId));
        if(!movieIdIsPresent)
        {
            throw new MovieNotFoundException();
        }
        user.setMovieList(movies);
        return iUserMovieRepository.save(user);
    }


    public Movie getMovieById(String email, int movieId) throws UserNotFoundException, MovieNotFoundException {
        Movie movieIdIsPresent;
        if(iUserMovieRepository.findById(email).isEmpty())
        {
            throw new UserNotFoundException();
        }
        User user = iUserMovieRepository.findById(email).get();
        List<Movie> movies = user.getMovieList();
        movieIdIsPresent = movies.get(movieId-1);

        return movieIdIsPresent;
    }


    public User updateMovieDetails(String email, int movieId, Movie movie) throws UserNotFoundException {
        User user = iUserMovieRepository.findByEmail(email);
        if (user != null) {
            List<Movie> movieList = user.getMovieList();
            for (int i = 0; i < movieList.size(); i++) {
                if (movieList.get(i).getMovieName().equals(movieId)){
                    movieList.set(i, movie);
                    break;
                }
            }
            user.setMovieList(movieList);
            iUserMovieRepository.save(user);
            return user;
        }
        throw new UserNotFoundException();
    }






















//    public Movie saveUserMovieToList(Movie movie){
//        return iUserMovieRepository.save(movie);
//    }


















//
//    public List<Movie> getAllMovies() {
//        return iUserMovieRepository.findAll();
//    }
}
