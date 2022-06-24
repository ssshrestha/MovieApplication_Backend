package com.example.UserAuthentication.security;

import com.example.UserAuthentication.entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTSecurityTokenGenerator implements ISecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {
        String jsonWebToken=null;

        JwtBuilder jwtBuilder= Jwts.builder();

        jsonWebToken=jwtBuilder.setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secret")
                .compact();

        Map<String,String> tokenMap=new HashMap<String,String>();
        tokenMap.put("token",jsonWebToken);
        tokenMap.put("message","User Successfully LoggedIn");

        return tokenMap;

    }
}
