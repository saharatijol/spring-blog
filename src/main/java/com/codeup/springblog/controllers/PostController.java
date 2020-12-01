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
        public String postsIndex(Model model) {
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post("The Bloggess", "Like Mother Teresa. Only better"));
        posts.add(new Post("Is this yours?", "An excerpt of account of always losing things."));
        posts.add(new Post("Late Enough", "An excerpt of account of always losing things."));
        posts.add(new Post("Parental Parody", "The leaning tower of Lego Pisa, and an epic catastrophe. Inconsolable"));
        posts.add(new Post("Generation Meh", "Let's talk about meh generation"));
        posts.add(new Post("Why I'm Always late", "“optimistic-people-have-one-thing-common-always-late”\n" +
                "\n" +
                "Intriguing. Nothing’s better than the headline, “The reason people are [bad quality that describes you] is actually because they’re [good quality].”"));
        model.addAttribute("postsIndex", posts);
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
