package com.example.BucketList.controller;

import com.example.BucketList.domain.User;
import com.example.BucketList.dtos.UserDTO;
import com.example.BucketList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping(value="/login")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        User user = userService.findByEmail(userDTO.getEmail());
        if(user != null) {
            return new ResponseEntity<>("Login successful!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Email or password are invalid!", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value="/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        User user = userService.findByEmail(userDTO.getEmail());
        if(user == null) {
            return new ResponseEntity<>("Register successful!", HttpStatus.OK);
        }
        return new ResponseEntity<>("You already have an account with that email address!", HttpStatus.UNAUTHORIZED);
    }
}
