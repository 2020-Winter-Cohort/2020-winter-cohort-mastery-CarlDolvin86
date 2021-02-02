package com.survivingcodingbootcamp.blog.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
public class Hashtag {
    @Id
    @GeneratedValue
    Long id;

    private String name;
    @ManyToMany
    private Collection<Post> hashtagPosts;

    public Hashtag(String name,Post...hashtagPosts) {
        this.name = name;
        this.hashtagPosts = List.of(hashtagPosts);
    }

    public Hashtag() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Post> getHashtagPosts() {
        return hashtagPosts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hashtag hashtag = (Hashtag) o;

        if (!Objects.equals(id, hashtag.id)) return false;
        return Objects.equals(name, hashtag.name);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
