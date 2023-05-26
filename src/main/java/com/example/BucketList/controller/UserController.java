package com.example.BucketList.controller;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.User;
import com.example.BucketList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("user/{userId}")
    public User saveDestination(@PathVariable("userId") Long userId, @RequestBody Destination destination) {
        return userService.saveDestination(userId, destination);
    }

    @DeleteMapping("user/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }
}
