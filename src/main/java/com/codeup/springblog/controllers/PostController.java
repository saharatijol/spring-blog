package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
        public String postsIndex(Model viewModel) {
        viewModel.addAttribute("postsIndex", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String show(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/show";
    }

    // CREATE - GET
    @GetMapping("/posts/create")
    public String showCreateForm() {
        return "posts/create";
    }

    // CREATE - POST
    @PostMapping("/posts/create")
    public String createNewPost(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ) {
        Post post = new Post(title, body, null);
        Post dbPost = postDao.save(post);
        return "redirect:/posts";
    }

    // DELETE
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        //System.out.println("id = " + id);
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    // EDIT - GET
    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable long id, Model viewModel) {
        viewModel.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    // EDIT - POST
    @PostMapping("/posts/{id}/edit")
    public String editPost(
            @PathVariable long id,
            @RequestParam(name="title") String title,
            @RequestParam(name="body") String body
            ){
        Post dbPost = postDao.getOne(id);
        dbPost.setTitle(title);
        dbPost.setBody(body);
        postDao.save(dbPost);
        return "redirect:/posts";
    }

    // USER SIGN UP - GET
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
}
