package com.example.BucketList.controller;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.Role;
import com.example.BucketList.domain.User;
import com.example.BucketList.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/{userId}/destination")
    public ResponseEntity<String> saveUserPrivateDestination(@PathVariable Long userId, @RequestBody Destination destination) {
        User user = userService.getSingleUser(userId);
        if(user != null) {
            List<Role> roles = user.getRoles();
            for (Role r : roles) {
                if (Objects.equals(r.getName(), "USER")) {
                    return new ResponseEntity<>(userService.savePrivateDestination(user, destination), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(userService.savePrivateDestination(user, destination), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/admin/{adminId}/destination")
    public ResponseEntity<String> saveAdminPublicDestination(@PathVariable Long adminId, @RequestBody Destination destination) {
        User user = userService.getSingleUser(adminId);
        if(user != null) {
            List<Role> roles = user.getRoles();
            for (Role r : roles) {
                if (Objects.equals(r.getName(), "ADMIN")) {
                    return new ResponseEntity<>(userService.savePublicDestination(destination), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(userService.savePublicDestination(destination), HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        User user = userService.getSingleUser(userId);
        if(user != null) {
            List<Role> roles = user.getRoles();
            for (Role r : roles) {
                if (Objects.equals(r.getName(), "USER")) {
                    return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(userService.deleteUser(userId), HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/admin/{adminId}/destination/{destinationId}")
    public ResponseEntity<String> deletePublicDestination(@PathVariable Long adminId, @PathVariable Long destinationId) {
        User user = userService.getSingleUser(adminId);
        if(user != null) {
            List<Role> roles = user.getRoles();
            for (Role r : roles) {
                if (Objects.equals(r.getName(), "ADMIN")) {
                    return new ResponseEntity<>(userService.deletePublicDestination(destinationId), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(userService.deletePublicDestination(destinationId), HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/user/{userId}/destination/{destinationId}")
    public ResponseEntity<String> deletePrivateDestination(@PathVariable Long userId, @PathVariable Long destinationId) {
        User user = userService.getSingleUser(userId);
        if(user != null) {
            List<Role> roles = user.getRoles();
            for (Role r : roles) {
                if (Objects.equals(r.getName(), "USER")) {
                    return new ResponseEntity<>(userService.deletePrivateDestination(user, destinationId), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(userService.deletePrivateDestination(user, destinationId), HttpStatus.UNAUTHORIZED);
    }

    /*@PutMapping("/user/{userId}/changePassword")
    public ResponseEntity<String> updatePassword(@PathVariable Long userId, @) {
        User user = userService.getSingleUser(userId);
        if(user != null) {
                return new ResponseEntity<>(userService.updatePassword(user, destination), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.savePrivateDestination(user, destination), HttpStatus.UNAUTHORIZED);
    }*/

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getSingleUser(userId), HttpStatus.OK);
    }
}
