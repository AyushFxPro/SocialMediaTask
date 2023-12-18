package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends MongoRepository<Like, String> {
    List<Like> findByUserIdInOrderByTimestampDesc(List<String> userIds);
    List<Like> findByUserId(String userId);
}
