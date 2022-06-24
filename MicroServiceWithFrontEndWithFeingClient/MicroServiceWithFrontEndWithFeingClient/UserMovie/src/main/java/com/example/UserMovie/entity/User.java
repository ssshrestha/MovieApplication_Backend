package com.example.UserMovie.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class User {
    @Id
   private String email;
   private String name;
   @Transient
   private String password;
   private String mobileNo;
   private List<Movie> movieList;

    public User() {
    }

    public User(String email, String name, String password, String mobileNo, List<Movie> movieList) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.mobileNo = mobileNo;
        this.movieList = movieList;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", movieList=" + movieList +
                '}';
    }
}
