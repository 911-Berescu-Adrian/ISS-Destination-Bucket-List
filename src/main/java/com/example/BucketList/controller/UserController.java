package com.example.BucketList.controller;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.User;
import com.example.BucketList.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private CustomerService userService;

    @PostMapping("user/{userId}/add")
    public User saveDestination(@PathVariable("userId") Long userId, @RequestBody Destination destination) {
        return userService.saveDestination(userId, destination);
    }
}
