package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Repost;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RepostRepository extends MongoRepository<Repost, String> {
    Optional<Repost> findByOriginalPostIdAndUserId(String originalPostId, String userId);
}
