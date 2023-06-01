package com.example.BucketList.service;

import com.example.BucketList.domain.Destination;
import com.example.BucketList.domain.User;
import com.example.BucketList.dtos.UserDTO;

import java.util.List;

public interface IUserService {
    User saveDestination(Long userId, Destination destination);

    void saveUser(UserDTO userDto);

    void deleteUser(Long userId);

    User findUserByEmail(String email);

    List<UserDTO> findAllUsers();
}