package com.example.UserAuthentication.controller;

import com.example.UserAuthentication.entity.User;
import com.example.UserAuthentication.exception.UserAlreadyExistsException;
import com.example.UserAuthentication.exception.UserNotFoundException;
import com.example.UserAuthentication.security.ISecurityTokenGenerator;
import com.example.UserAuthentication.security.JWTSecurityTokenGenerator;
import com.example.UserAuthentication.service.UserAuthenticationService;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    UserAuthenticationService userAuthenticationService;
    @Autowired
    JWTSecurityTokenGenerator jwtSecurityTokenGenerator;

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
        User userObj= userAuthenticationService.saveUser(user);
        return new ResponseEntity<String>("User Registered", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFoundException
    {
        ResponseEntity responseEntity;

        try {
            User userObj=userAuthenticationService.findUserByCustomerIdAndPassword(user.getEmail(),user.getPassword());
        if(userObj.getEmail().equals(user.getEmail())){
            Map<String,String> tokenMap=jwtSecurityTokenGenerator.generateToken(userObj);
            responseEntity=new ResponseEntity<>(tokenMap, HttpStatus.OK);
        } else if (userObj.getEmail().isEmpty()) {
                responseEntity=new ResponseEntity<>("InValid Credentials",HttpStatus.UNAUTHORIZED);
        } else{
            responseEntity=new ResponseEntity<>("InValid User",HttpStatus.NOT_FOUND);
        }
        }
        catch (UserNotFoundException ue) {
            throw new RuntimeException(ue);
        }
        catch(Exception e)
        {
            responseEntity=new ResponseEntity<>("Some Error Occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
