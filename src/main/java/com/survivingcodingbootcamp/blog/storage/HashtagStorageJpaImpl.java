package com.survivingcodingbootcamp.blog.storage;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.storage.repository.HashtagRepository;
import org.springframework.stereotype.Service;

@Service
public class HashtagStorageJpaImpl implements HashtagStorage {
    HashtagRepository hashtagRepository;
   public HashtagStorageJpaImpl(HashtagRepository hashtagRepository) {
       this.hashtagRepository = hashtagRepository;
   }

    @Override
    public Iterable<Hashtag> retrieveAllHashtags() {
        return hashtagRepository.findAll();
    }

    @Override
    public Hashtag retrieveHashtagById(long l) {
        return hashtagRepository.findById(l).get();
    }

    @Override
    public void save(Hashtag hashtagToAdd) {
        hashtagRepository.save(hashtagToAdd);
    }

    @Override
    public void addPost(Long id, Post post) {

    }
}
