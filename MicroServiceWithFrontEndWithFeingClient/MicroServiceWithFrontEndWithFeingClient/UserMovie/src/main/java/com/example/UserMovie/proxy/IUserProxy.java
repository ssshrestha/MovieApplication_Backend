package com.example.UserMovie.proxy;

import com.example.UserMovie.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AUTH-MICRO",url="localhost:8085")
public interface IUserProxy {
    @PostMapping("/api/v1/register")
    public ResponseEntity<String> saveUser(@RequestBody User user);
}
