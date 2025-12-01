package com.musicstore.controller;

import com.musicstore.entity.Admin;
import com.musicstore.entity.Role;
import com.musicstore.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(required = false) String error,
                                @RequestParam(required = false) String logout,
                                Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Incorrect email or password");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "You have been logged out successfully");
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               @RequestParam(required = false) boolean remember,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {
        if (adminService.authenticate(email, password)) {
            request.getSession().setAttribute("adminEmail", email);
            return "redirect:/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Incorrect email or password");
            return "redirect:/login";
        }
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("roles", adminService.getAllRoles());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistration(@RequestParam String email,
                                      @RequestParam String password,
                                      @RequestParam String fullName,
                                      @RequestParam Role role,
                                      RedirectAttributes redirectAttributes) {
        try {
            adminService.registerAdmin(email, password, fullName, role);
            redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please login.");
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            redirectAttributes.addFlashAttribute("email", email);
            redirectAttributes.addFlashAttribute("fullName", fullName);
            return "redirect:/register";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        String email = (String) session.getAttribute("adminEmail");
        if (email == null) {
            return "redirect:/login";
        }

        adminService.findByEmail(email).ifPresent(admin -> {
            model.addAttribute("adminName", admin.getFullName());
            model.addAttribute("adminRole", admin.getRole().getDisplayName());
        });

        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }
}