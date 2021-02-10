package com.survivingcodingbootcamp.blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.List;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToMany
    public Collection<Post>commentPost;

    public Comment(String name,Post...commentPost) {
        this.name = name;
        this.commentPost = List.of(commentPost);
    }

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Post> getCommentPost() {
        return commentPost;
    }

    public void setCommentPost(Collection<Post> commentPost) {
        this.commentPost = commentPost;
    }
}