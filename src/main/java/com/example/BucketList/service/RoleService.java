package com.example.BucketList.service;

import com.example.BucketList.domain.Role;
import com.example.BucketList.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.roleRepository.deleteById(roleId);
    }
}
