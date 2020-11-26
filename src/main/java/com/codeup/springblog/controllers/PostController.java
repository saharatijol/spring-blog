package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String postsIndex() {
        return "Posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id) {
        return "View individual post with an id: " + id;
    }

    // GET request
    @GetMapping("/posts/create")
    @ResponseBody
    public String showCreateForm() {
        return "View the form for creating a post";
    }
    // POST request
    @PostMapping("/posts/create")
    @ResponseBody
    public String createNewPost() {
        return "Create a new post";
    }
}
