package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class UserController {

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "users/signup";
    }

    @PostMapping("/signup")
    public String signUp(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password
    ) {
        User user = new User(username, email, password);
        User dbUser = userDao.save(user);
        return "redirect:/posts";
    }

    // LOGIN - GET
    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }
}
