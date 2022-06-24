package com.example.UserAuthentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT,reason = "User Already Exists For The Given Credentials")
public class UserAlreadyExistsException extends Exception {
}
