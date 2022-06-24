package com.example.UserAuthentication.security;

import com.example.UserAuthentication.entity.User;

import java.util.Map;

public interface ISecurityTokenGenerator {
    Map<String, String> generateToken(User user);
}
