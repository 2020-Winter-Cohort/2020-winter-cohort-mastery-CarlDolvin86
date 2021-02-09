package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.storage.HashtagStorage;
import com.survivingcodingbootcamp.blog.storage.PostStorage;
import com.survivingcodingbootcamp.blog.storage.TopicStorage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/topics")
public class TopicController {

    private TopicStorage topicStorage;
    private HashtagStorage hashtagStorage;
    private PostStorage postStorage;

    public TopicController(TopicStorage topicStorage, HashtagStorage hashtagStorage, PostStorage postStorage) {
        this.topicStorage = topicStorage;
        this.hashtagStorage = hashtagStorage;
        this.postStorage = postStorage;
    }

    @GetMapping("/{id}")
    public String displaySingleTopic(@PathVariable long id, Model model) {
        model.addAttribute("topic", topicStorage.retrieveSingleTopic(id));
        return "single-topic-template";
    }

    @PostMapping("addedPost")
    public String addNewPost(/*@PathVariable Long id, */@RequestParam String title, @RequestParam String topicID, @RequestParam String author, @RequestParam String content/*, @RequestParam String hashtag*/) {
        Long tID = Long.parseLong(topicID);
        Topic topic = topicStorage.retrieveSingleTopic(tID);


        Post addedPost = new Post(title, topic, content, author);
        postStorage.save(addedPost);
        return "redirect:/topics/" + topicID;
    }
}