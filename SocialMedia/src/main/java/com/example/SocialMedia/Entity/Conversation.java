package com.example.SocialMedia.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "conversations")
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private List<String> participantIds; // User IDs participating in the conversation
    private List<Message> messages = new ArrayList<>();
}
