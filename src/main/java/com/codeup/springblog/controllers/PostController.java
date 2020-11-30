package com.codeup.springblog.controllers;

import com.codeup.springblog.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    @RequestMapping(value="/posts", method = RequestMethod.GET)
        public String postsIndex(Model model) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("Do you see this? 1", "test 1"));
        posts.add(new Post("Do you see this? 2", "test 2"));
        posts.add(new Post("Do you see this? 3", "test 3"));
        model.addAttribute("postsIndex", posts);
        return "posts/index";
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
