package com.example.BucketList.service;

import com.example.BucketList.domain.Admin;
import com.example.BucketList.domain.Destination;
import com.example.BucketList.repository.IAdminRepository;
import com.example.BucketList.repository.IDestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private IAdminRepository adminRepository;

    @Autowired
    private IDestinationRepository destinationRepository;

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getSingleAdmin(Long AdminId) {
        return adminRepository.findById(AdminId).get();
    }

    public Admin findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public String saveDestination(Destination destination) {
        destinationRepository.save(destination);
        return "Destination saved!";
    }

    public String deleteDestination(Long destinationId) {
        destinationRepository.deleteById(destinationId);
        return "Destination deleted!";
    }
}