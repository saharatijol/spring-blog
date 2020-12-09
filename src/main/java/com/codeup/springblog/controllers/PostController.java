package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
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
        User userDb = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postToBeSaved.setOwner(userDb);
        Post dbPost = postDao.save(postToBeSaved);
        emailService.prepareAndSend(dbPost, "Post has been created", "You can find it with the id of " + dbPost.getId());
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
        User userDb = userDao.getOne(1L); // ideally, this will ba a user obj coming from a session
        postToEdit.setOwner(userDb);
        postDao.save(postToEdit);
        return "redirect:/posts";
    }
}
