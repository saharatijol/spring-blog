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
    public String showCreateForm(Model viewModel) {
        viewModel.addAttribute("post", new Post());
        return "posts/create";
    }

    // CREATE - POST
    @PostMapping("/posts/create")
    public String createNewPost(@ModelAttribute Post postToBeSaved) {
        User userDb = userDao.getOne(1L);
        postToBeSaved.setOwner(userDb);
        Post dbPost = postDao.save(postToBeSaved);
        return "redirect:/posts/" + dbPost.getId();
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
    public String editPost(@ModelAttribute Post postToEdit){
        User userDb = userDao.getOne(1L);
        postToEdit.setOwner(userDb);
        Post dbPost = postDao.save(postToEdit);
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
