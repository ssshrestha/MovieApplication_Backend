package com.example.UserAuthentication.service;


import com.example.UserAuthentication.entity.User;
import com.example.UserAuthentication.exception.UserNotFoundException;
import com.example.UserAuthentication.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationService {

    @Autowired
    IUserRepository iUserRepository;

    public User saveUser(User user){
        System.out.println("Inside User Service");
        return iUserRepository.save(user);
    }

    public User findUserByCustomerIdAndPassword(String email, String password) throws UserNotFoundException {
        User user=iUserRepository.findUserByEmailAndPassword(email,password);
        if (user==null){
            throw new UserNotFoundException();
        }
        return user;
    }
}
