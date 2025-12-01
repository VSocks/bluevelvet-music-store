package com.musicstore.service;

import com.musicstore.entity.Admin;
import com.musicstore.entity.Role;
import com.musicstore.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean validatePassword(String password) {
        return password.length() >= 8;
    }

    public Admin registerAdmin(String email, String password, String fullName, Role role) {
        if (!validatePassword(password)) {
            throw new IllegalArgumentException("Password must be at least 8 characters long");
        }

        if (adminRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already registered");
        }

        Admin admin = new Admin(email, passwordEncoder.encode(password), fullName, role);
        return adminRepository.save(admin);
    }

    public boolean authenticate(String email, String password) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return passwordEncoder.matches(password, admin.getPassword());
        }
        return false;
    }

    public Optional<Admin> findByEmail(String email) {
        return adminRepository.findByEmail(email);
    }

    public List<Role> getAllRoles() {
        return Arrays.asList(Role.values());
    }
}