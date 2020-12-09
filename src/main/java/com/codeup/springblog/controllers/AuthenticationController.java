package com.codeup.springblog.controllers;


import org.springframework.security.authentication.AnonymousAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginForm() {
        // Skip login page if user is authenticated
        Authentication token = SecurityContextHolder.getContext().getAuthentication();

        // User NOT logged in
        if (token instanceof AnonymousAuthenticationToken) {
            return "users/login";

            // redirect to /posts if logged in
        } return String.format("redirect:%s", "/posts");
    }
}
