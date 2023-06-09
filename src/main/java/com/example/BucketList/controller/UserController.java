package com.example.BucketList.controller;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.Role;
import com.example.BucketList.domain.User;
import com.example.BucketList.dtos.UserPasswordDTO;
import com.example.BucketList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/{userId}/destination")
    public ResponseEntity<String> saveUserPrivateDestination(@PathVariable Long userId, @RequestBody Destination destination) {
        User user = userService.getSingleUser(userId);
        if (user != null) {
            List<Role> roles = user.getRoles();
            for (Role r : roles) {
                if (Objects.equals(r.getName(), "USER")) {
                    return new ResponseEntity<>(userService.savePrivateDestination(user, destination), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(userService.savePrivateDestination(user, destination), HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        User user = userService.getSingleUser(userId);
        if (user != null) {
            List<Role> roles = user.getRoles();
            for (Role r : roles) {
                if (Objects.equals(r.getName(), "USER")) {
                    return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.UNAUTHORIZED);
    }

    @PutMapping(value = "/user/{userId}/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updatePassword(@PathVariable Long userId, @RequestBody UserPasswordDTO userPasswordDTO) {
        User user = userService.getSingleUser(userId);
        if(user != null) {
            return new ResponseEntity<>(userService.updatePassword(userId, userPasswordDTO), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.updatePassword(userId, userPasswordDTO), HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getSingleUser(userId), HttpStatus.OK);
    }

}