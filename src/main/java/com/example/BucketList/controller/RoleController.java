package com.example.BucketList.controller;

import com.example.BucketList.domain.Role;
import com.example.BucketList.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public Role saveDestination(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @DeleteMapping("/role/{roleId}")
    public String deleteDestination(@PathVariable("roleId") Long roleId) {
        roleService.deleteRole(roleId);
        return "Car successfully deleted";
    }
}
