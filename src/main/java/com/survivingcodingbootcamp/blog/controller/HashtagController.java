package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.HashSet;

@Controller
public class HashtagController {

    private HashtagStorage hashtagStorage;
    private PostStorage postStorage;

    public HashtagController(HashtagStorage hashtagStorage, PostStorage postStorage) {
        this.hashtagStorage = hashtagStorage;
        this.postStorage = postStorage;
    }

    @RequestMapping("/hashtags")
    public String displayAllHashtags(Model model) {
        model.addAttribute("hashtags", hashtagStorage.retrieveAllHashtags());
        return "all-hashtags-template";
    }

    @GetMapping("hashtag/{id}")
    public String displaySingleHashtag(@PathVariable Long id, Model model) {
        model.addAttribute("hashtag", hashtagStorage.retrieveHashtagById(id));
        return "single-hashtag-template";
    }

    @PostMapping("updateHashtags")
    public String addHashtag(@RequestParam String name, @RequestParam String postId) {
        Long newId = Long.parseLong(postId);
        Iterable<Hashtag> hashtags = hashtagStorage.retrieveAllHashtags();
        Hashtag addedHashtag = null;
        Hashtag addedToAllHashtag = null;

      for(Hashtag i: postStorage.retrievePostById(newId).getHashtag()){
            if(i.getName().equals(name)) {
                addedHashtag = i;
            }
        }

        for(Hashtag i: hashtags){
            if(i.getName().equals(name)) {
                addedToAllHashtag = i;
            }
        }
       
        if(addedHashtag == null && addedToAllHashtag == null && !name.equals("")) {

            addedHashtag = new Hashtag(name, postStorage.retrievePostById(newId));
            hashtagStorage.save(addedHashtag);
        } else if(addedToAllHashtag != null) {
           hashtagStorage.addPost(addedToAllHashtag.getId(), postStorage.retrievePostById(newId));
        }

    return "redirect:/posts/" + newId;
    }
}
