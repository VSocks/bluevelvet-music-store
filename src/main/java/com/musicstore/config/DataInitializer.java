package com.musicstore.config;

import com.musicstore.entity.Admin;
import com.musicstore.entity.Role;
import com.musicstore.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create default admin if none exists
        if (adminRepository.count() == 0) {
            Admin admin = new Admin();
            admin.setEmail("admin@musicstore.com");
            admin.setPassword(passwordEncoder.encode("password123"));
            admin.setFullName("Music Store Administrator");
            admin.setRole(Role.ADMINISTRATOR);

            adminRepository.save(admin);
            System.out.println("Default admin created: admin@musicstore.com / password123");

            // Create sample users for each role (optional)
            createSampleUsers();
        }
    }

    private void createSampleUsers() {
        // Sales Manager
        Admin salesManager = new Admin(
                "sales@musicstore.com",
                passwordEncoder.encode("password123"),
                "Sales Manager",
                Role.SALES_MANAGER
        );
        adminRepository.save(salesManager);

        // Editor
        Admin editor = new Admin(
                "editor@musicstore.com",
                passwordEncoder.encode("password123"),
                "Content Editor",
                Role.EDITOR
        );
        adminRepository.save(editor);

        // Assistant
        Admin assistant = new Admin(
                "assistant@musicstore.com",
                passwordEncoder.encode("password123"),
                "Store Assistant",
                Role.ASSISTANT
        );
        adminRepository.save(assistant);

        // Shipping Manager
        Admin shippingManager = new Admin(
                "shipping@musicstore.com",
                passwordEncoder.encode("password123"),
                "Shipping Manager",
                Role.SHIPPING_MANAGER
        );
        adminRepository.save(shippingManager);

        System.out.println("Sample users created for each role");
    }
}