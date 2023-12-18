package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByUserIdInOrderByTimestampDesc(List<String> friendIds);
    List<Comment> findByUserId(String userId);
}
