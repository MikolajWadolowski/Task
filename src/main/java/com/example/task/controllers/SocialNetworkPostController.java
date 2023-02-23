package com.example.task.controllers;

import com.example.task.models.SocialNetworkPost;
import com.example.task.services.SocialNetworkPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class SocialNetworkPostController {
    @Autowired
    SocialNetworkPostService socialNetworkPostService;

    @GetMapping("")
    public ResponseEntity<List<SocialNetworkPost>> getSocialNetworkPosts() {
        List<SocialNetworkPost> socialNetworkPosts = socialNetworkPostService.getAllSocialNetworkPosts();
        return new ResponseEntity<>(socialNetworkPosts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocialNetworkPost> getSocialNetworkPostsById(@PathVariable("id") long id) {
        SocialNetworkPost socialNetworkPost = socialNetworkPostService.getSocialNetworkPostById(id);
        if (socialNetworkPost == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        socialNetworkPost = socialNetworkPostService.increaseViewCountByOne(socialNetworkPost);
        return ResponseEntity.ok(socialNetworkPost);
    }

    @GetMapping("/top10")
    public ResponseEntity<List<SocialNetworkPost>> getTop10SocialNetworkPosts() {
        List<SocialNetworkPost> socialNetworkPosts = socialNetworkPostService.getTop10PostsByViewCount();
        return new ResponseEntity<>(socialNetworkPosts, HttpStatus.OK);
    }

    // Here this method realistically should only allow content and author should be provided from the session
    // viewCount and postDate are metaData ofc so in real scenario it would be implemented in a different way
    // In such case I would create a Dto with only allowed parameters
    @PostMapping("")
    public ResponseEntity<SocialNetworkPost> createSocialNetworkPost(@RequestBody SocialNetworkPost socialNetworkPost) {
        // I am setting server time as postdate
        socialNetworkPost.setPostDate(LocalDate.now());
        SocialNetworkPost newPost = socialNetworkPostService.createSocialNetworkPost(socialNetworkPost);
        return ResponseEntity.ok(newPost);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SocialNetworkPost> updateSocialNetworkPost(@PathVariable("id") long id, @RequestBody SocialNetworkPost socialNetworkPost) {
        SocialNetworkPost updatePost = socialNetworkPostService.updateSocialNetworkPost(id, socialNetworkPost);
        if (updatePost != null) {
            return new ResponseEntity<>(updatePost, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteSocialNetworkPost(@PathVariable("id") long id) {
       if (socialNetworkPostService.deleteSocialNetworkPost(id)){
           return new ResponseEntity<>(HttpStatus.OK);
       } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
