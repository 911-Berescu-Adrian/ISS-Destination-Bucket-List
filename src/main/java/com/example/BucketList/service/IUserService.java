package com.example.BucketList.service;

import com.example.BucketList.domain.User;
import com.example.BucketList.dtos.UserDTO;

import java.util.List;

public interface IUserService {
    void saveUser(UserDTO userDto);

    User findUserByEmail(String email);

    List<UserDTO> findAllUsers();
}
