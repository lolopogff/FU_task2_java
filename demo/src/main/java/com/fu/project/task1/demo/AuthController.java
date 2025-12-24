package com.fu.project.task1.demo;

import com.fu.project.task1.demo.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @Autowired
    private AppUserService userService;

    // Страница регистрации
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new AppUser());
        return "register"; // register.html
    }

    // Обработка регистрации
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           RedirectAttributes redirectAttributes) {
        try {
            userService.registerUser(username, password);
            redirectAttributes.addFlashAttribute("message", "Регистрация успешна! Войдите.");
            return "redirect:/login";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    // Страница логина (Spring Security использует /login по умолчанию)
    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }
}