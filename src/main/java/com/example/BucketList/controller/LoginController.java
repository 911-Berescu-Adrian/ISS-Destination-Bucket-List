package com.example.BucketList.controller;

import com.example.BucketList.domain.Role;
import com.example.BucketList.domain.User;
import com.example.BucketList.dtos.UserDTO;
import com.example.BucketList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody UserDTO request) {
        Optional<User> user = Optional.ofNullable(userService.findByEmail(request.getEmail()));
        List<Role> roles = user.get().getRoles();

        boolean userRole = false;
        boolean adminRole = false;

        for(Role role : roles) {
            if(Objects.equals(role.getName(), "USER")) {
                userRole = true;
            }
            if(Objects.equals(role.getName(), "ADMIN")) {
                adminRole = true;
            }
        }

        if (Objects.equals(request.getPassword(), user.get().getPassword())) {
            if(userRole) {
                return new ResponseEntity<>("USER", HttpStatus.OK);
            } else if(adminRole) {
                return new ResponseEntity<>("ADMIN", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Email or password is invalid", HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody UserDTO request) {
        User user = userService.findByEmail(request.getEmail());
        if (user == null) {
            userService.saveUser(request);
            return new ResponseEntity<>("Register successful!", HttpStatus.OK);
        }
        return new ResponseEntity<>("You already have an account with that email address!", HttpStatus.UNAUTHORIZED);
    }
}