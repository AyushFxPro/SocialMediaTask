package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message,String> {
    List<Message> findBySenderIdAndTimestampAfter(String senderId, LocalDateTime timestamp);
}
