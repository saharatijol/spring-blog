package com.codeup.springblog;

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
    public String individualPost(@PathVariable int id) {
        return "View individual post with an id: " + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String create() {
        return "View the form for creating a post";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String createNewPost() {
        return "Create a new post";
    }


}
