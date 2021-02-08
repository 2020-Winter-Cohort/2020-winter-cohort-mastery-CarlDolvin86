package com.survivingcodingbootcamp.blog.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @ManyToOne
    private Topic topic;
    @Lob
    private String content;
    @ManyToMany(mappedBy = "posts")
    Collection<HashTag> hashtags;
    private String author;



    protected Post() {
    }

    public Post(String title, String author, Topic topic, String content, HashTag ...hashtags) {
        this.title = title;
        this.topic = topic;
        this.content = content;
        this.author = author;
        this.hashtags = List.of(hashtags);
    }



    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Collection<HashTag> getHashTagCollection(){
        return hashtags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", topic=" + topic +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != null ? !id.equals(post.id) : post.id != null) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (topic != null ? !topic.equals(post.topic) : post.topic != null) return false;
        return content != null ? content.equals(post.content) : post.content == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
