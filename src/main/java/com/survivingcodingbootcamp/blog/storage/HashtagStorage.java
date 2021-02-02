package com.survivingcodingbootcamp.blog.storage;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.storage.repository.HashtagRepository;
import org.springframework.stereotype.Service;

@Service
public class HashtagStorage {
    private HashtagRepository hashtagRepository;

    public HashtagStorage(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public void saveHashtag(Hashtag hashtag){
        hashtagRepository.save(hashtag);
    }

    public Iterable<Hashtag> retrieveAllHashtags(){
        return hashtagRepository.findAll();
    }

    public Hashtag retrieveSingleHashtag(Long id){
        return hashtagRepository.findById(id).get();
    }

    public Hashtag getHashtagByName(String name){
        for(Hashtag tag : retrieveAllHashtags()){
            if(tag.getName().equals(name)){
                return tag;
            }
        }
        return null;
    }


}
