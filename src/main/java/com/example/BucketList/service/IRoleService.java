package com.example.BucketList.service;

import com.example.BucketList.domain.Role;

public interface IRoleService {
    Role saveRole(Role role);

    void deleteRole(Long roleId);
}
