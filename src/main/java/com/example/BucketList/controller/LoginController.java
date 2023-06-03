package com.example.BucketList.controller;

import com.example.BucketList.domain.User;
import com.example.BucketList.dtos.UserDTO;
import com.example.BucketList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/login", method= RequestMethod.GET)
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        User user = userService.findByEmail(userDTO.getEmail());
        if(user != null) {
            return new ResponseEntity<String>("Login successful", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Email or password are invalid!", HttpStatus.UNAUTHORIZED);
    }
}
