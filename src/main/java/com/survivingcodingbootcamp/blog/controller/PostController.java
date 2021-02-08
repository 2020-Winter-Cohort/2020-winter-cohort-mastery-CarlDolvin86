package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Comment;
import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.storage.*;
import com.survivingcodingbootcamp.blog.storage.repository.commentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Properties;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostStorage postStorage;

    private TopicStorage topicStorage;
    private commentRepository commentRepo;

    public PostController(PostStorage postStorage, TopicStorage topicStorage,commentRepository commentRepo) {
        this.postStorage = postStorage;
       this.topicStorage=topicStorage;
       this.commentRepo=commentRepo;

    }
@RequestMapping("/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postStorage.retrievePostById(id));
        return "single-post-template";
    }
@PostMapping("addPost/{id}")
    public String addNewPost (@PathVariable long id,@RequestParam String newContent, @RequestParam String newTitle,@RequestParam String newAuthor){

    Post addPost = new Post(newTitle,topicStorage.retrieveSingleTopic(id),newContent,newAuthor);
      postStorage.save(addPost);
      return "redirect:/topics/{id}";

    }
