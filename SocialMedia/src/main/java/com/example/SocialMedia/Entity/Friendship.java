package com.example.SocialMedia.Entity;

import com.example.SocialMedia.Enum.FriendshipStatus;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "friendships")
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @DBRef
    private User user;

    @DBRef
    private User friend;

    private FriendshipStatus status;
}
