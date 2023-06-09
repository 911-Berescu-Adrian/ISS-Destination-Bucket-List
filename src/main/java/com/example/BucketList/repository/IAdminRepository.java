package com.example.BucketList.repository;

import com.example.BucketList.domain.Admin;
import com.example.BucketList.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email);
}
