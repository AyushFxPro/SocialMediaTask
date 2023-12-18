package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Conversation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {
    Optional<Conversation> findByParticipantIdsContainsAndParticipantIdsContains(String userId1, String userId2);
    List<Conversation> findByParticipantIdsContains(String userId);
}
