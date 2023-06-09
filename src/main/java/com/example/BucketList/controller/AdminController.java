package com.example.BucketList.controller;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.Admin;
import com.example.BucketList.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/destination", method = RequestMethod.POST)
    public ResponseEntity<String> saveDestination(@RequestBody Destination destination) {
        return new ResponseEntity<>(adminService.saveDestination(destination), HttpStatus.OK);
    }

    @DeleteMapping("/destination/{destinationId}")
    public ResponseEntity<String> deleteDestination(@PathVariable Long destinationId) {
        return new ResponseEntity<>(adminService.deleteDestination(destinationId), HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<List<Admin>> getAllAdmins() {
        return new ResponseEntity<>(adminService.getAllAdmins(), HttpStatus.OK);
    }
}
