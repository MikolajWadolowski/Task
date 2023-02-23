package com.example.task.services;

import com.example.task.models.SocialNetworkPost;
import com.example.task.repositories.SocialNetworkPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SocialNetworkPostService {
    @Autowired
    private SocialNetworkPostRepository socialNetworkPostRepository;

    public List<SocialNetworkPost> getAllSocialNetworkPosts () { return socialNetworkPostRepository.findAll(); }

    public SocialNetworkPost createSocialNetworkPost(SocialNetworkPost socialNetworkPost){
        try{
            return socialNetworkPostRepository.save(socialNetworkPost);
        } catch (Exception e) {
            return null;
        }
    }

    public SocialNetworkPost getSocialNetworkPostById (long id) {
        return socialNetworkPostRepository.findById(id)
                .orElse(null);
    }

    public List<SocialNetworkPost> getTop10PostsByViewCount () {
        return socialNetworkPostRepository.findTop10ByOrderByViewCountDesc();
    }

    public SocialNetworkPost increaseViewCountByOne (SocialNetworkPost socialNetworkPost) {
        socialNetworkPost.setViewCount(socialNetworkPost.getViewCount()+1);
        return socialNetworkPostRepository.save(socialNetworkPost);
    }

    public SocialNetworkPost updateSocialNetworkPost(long id, SocialNetworkPost postEdit) {
        return socialNetworkPostRepository.findById(id)
                .map(socialNetworkPost -> socialNetworkPostRepository.save(socialNetworkPost.update(postEdit)))
                .orElse(null);
    }

    public boolean deleteSocialNetworkPost(long id) {
       if (getSocialNetworkPostById(id) == null) {
           return false;
       }
       socialNetworkPostRepository.deleteById(id);
       return true;
    }

}
