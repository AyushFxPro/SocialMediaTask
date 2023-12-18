package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends MongoRepository<Follow,String> {
    List<String> findFolloweesByFollowerId(String userId);
}
