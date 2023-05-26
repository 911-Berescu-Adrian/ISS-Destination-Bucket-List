package com.example.BucketList.controller;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("admin/add")
    public Destination addDestination(@RequestBody Destination destination) {
        return adminService.saveDestination(destination);
    }
}
