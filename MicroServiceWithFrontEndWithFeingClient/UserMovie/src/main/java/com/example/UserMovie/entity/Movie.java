package com.example.UserMovie.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//@Document:- Movie Documents will be stored as embedded document
public class Movie {
    @Id
    private String movieId;
    private String movieName;
    public Movie() {
    }

    public Movie(String movieId, String movieName) {
        this.movieId = movieId;
        this.movieName = movieName;

    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                '}';
    }
}
