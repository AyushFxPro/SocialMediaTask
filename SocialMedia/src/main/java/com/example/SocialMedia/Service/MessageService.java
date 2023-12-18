package com.example.SocialMedia.Service;

import com.example.SocialMedia.Repository.ConversationRepository;
import com.example.SocialMedia.Entity.Message;
import com.example.SocialMedia.Entity.Conversation;
import com.example.SocialMedia.Repository.MessageRepository;
import com.example.SocialMedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ConversationRepository conversationRepository;

    public List<Message> getMessagesBetweenUsers(String senderId, String receiverId) {
        // Fetch or create a conversation between two users
        Conversation conversation = getOrCreateConversation(senderId, receiverId);

        // Return the messages in the conversation
        return conversation.getMessages();
    }

    public Message sendMessage(String senderId, String receiverId, String content) {
        // Fetch or create a conversation between two users
        Conversation conversation = getOrCreateConversation(senderId, receiverId);

        // Create a new message
        Message message = new Message();
        message.setSenderId(senderId);
        message.setContent(content);
        message.setTimestamp(LocalDateTime.now());

        // Add the message to the conversation and save
        conversation.getMessages().add(message);
        conversationRepository.save(conversation);

        return message;
    }

    private Conversation getOrCreateConversation(String userId1, String userId2) {
        // Try to find an existing conversation
        Optional<Conversation> existingConversation = conversationRepository.findByParticipantIdsContainsAndParticipantIdsContains(userId1, userId2);

        return existingConversation.orElseGet(() -> {
            // If no existing conversation, create a new one
            List<String> participantIds = Arrays.asList(userId1, userId2);
            Conversation newConversation = new Conversation();
            newConversation.setParticipantIds(participantIds);
            return conversationRepository.save(newConversation);
        });
    }
}
