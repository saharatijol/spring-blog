package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.AdRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

public class AdController {

    private final AdRepository adDao;

    public AdController(AdRepository adDao) {
        this.adDao = adDao;
    }

    @RequestMapping(value="/ads", method = RequestMethod.GET)
    public String adsIndex(Model model) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("Do you see this? 1", "test 1"));
        posts.add(new Post("Do you see this? 2", "test 2"));
        model.addAttribute("postsIndex", posts);
        return "posts/index";
    }

    @GetMapping("/ads/{id}")
    public String show(@PathVariable long id, Model model) {
        Post post = new Post("First blog post title", "Here is the blog post body");
        model.addAttribute("post", post);
        return "posts/show";
    }

    // GET request
    @GetMapping("/ads/create")
    @ResponseBody
    public String showCreateForm() {
        return "View the form for creating a post";
    }
    // POST request
    @PostMapping("/ads/create")
    @ResponseBody
    public String createAd() {
        Ad ad = new Ad("title", "ps5");
        Ad dbAd = adDao.save(ad); // Ad from the DB
        return "Create a new Ad with the id: " + dbAd.getId();
    }
}
