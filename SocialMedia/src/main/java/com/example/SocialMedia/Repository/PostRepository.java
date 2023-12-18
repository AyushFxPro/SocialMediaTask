package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    List<Post> findByUserIdInOrderByTimestampDesc(List<String> friendIds);

    List<Post> findByUserId(String ownerId);
}
