package com.example.task.repositories;

import com.example.task.models.SocialNetworkPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialNetworkPostRepository extends JpaRepository<SocialNetworkPost, Long> {
    // gets top 10 posts by view count
    List<SocialNetworkPost> findTop10ByOrderByViewCountDesc();
}
