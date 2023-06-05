package com.example.BucketList.controller;

import com.example.BucketList.domain.User;
import com.example.BucketList.dtos.UserDTO;
import com.example.BucketList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody UserDTO request) {
        User user = userService.findByEmail(request.getEmail());
        if (user != null) {
            if (Objects.equals(request.getPassword(), user.getPassword())) {
                return new ResponseEntity<>("Login successful!", HttpStatus.OK);
            }
            return new ResponseEntity<>("Email or password is invalid", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Email is invalid!", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        User user = userService.findByEmail(userDTO.getEmail());
        if (user == null) {
            return new ResponseEntity<>("Register successful!", HttpStatus.OK);
        }
        return new ResponseEntity<>("You already have an account with that email address!", HttpStatus.UNAUTHORIZED);
    }
}
