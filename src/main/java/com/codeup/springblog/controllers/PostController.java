package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
        public String postsIndex(Model viewModel) {
        viewModel.addAttribute("postsIndex", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model model) {
        Post post = new Post("First blog post title", "Here is the blog post body");
        model.addAttribute("post", post);
        return "posts/show";
    }

    // GET request
    @GetMapping("/posts/create")
    public String showCreateForm() {
        return "posts/create";
    }

    // POST request
    @PostMapping("/posts/create")
    public String createNewPost(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ) {
        Post post = new Post(title, body);
        Post dbPost = postDao.save(post);
        return "redirect:/posts";
    }


    // DELETE
    @PostMapping("/posts/{id}")
    public String delete(@RequestParam(name="delete") long id) {
        System.out.println("id = " + id);
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    // EDIT - GET

    // EDIT - POST

}
