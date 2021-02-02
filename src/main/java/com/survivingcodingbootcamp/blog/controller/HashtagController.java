package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HashtagController {
    private HashtagStorage hashtagStorage;
    private PostStorage postStorage;

    public HashtagController(HashtagStorage hashtagStorage,PostStorage postStorage) {
        this.hashtagStorage = hashtagStorage;
        this.postStorage = postStorage;
    }

    @RequestMapping("/hashtags")
    public String getAllHashtags(Model model){
        model.addAttribute("hashtags",hashtagStorage.retrieveAllHashtags());
        return "all-hashtag-template";
    }

    @RequestMapping("/hashtag/{id}")
    public String getSingleHashtag(Model model, @PathVariable Long id){
        model.addAttribute("singleTagPosts",hashtagStorage.retrieveSingleHashtag(id).getHashtagPosts());
        return "single-hashtag-template";
    }

    @PostMapping("addHashtag/{id}")
    public String addHashtagToPost(@PathVariable Long id, @RequestParam String newHashtag){
        Post thePost = postStorage.retrievePostById(id);
        Hashtag addHashtag = new Hashtag(newHashtag,thePost);
        hashtagStorage.saveHashtag(addHashtag);
        return "redirect:/posts/{id}";
    }

}
