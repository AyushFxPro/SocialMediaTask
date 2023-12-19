package com.example.SocialMedia.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "user_profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @DBRef
    private User user;

    private String bio;
    private String profilePicture;
    @DBRef
    private List<Repost> reposts = new ArrayList<>();
    private List<String> posts=new ArrayList<>();
    @DBRef(lazy = true)
    private List<String> friendsId;



    @DBRef(lazy = true)
    private List<Follow> followers;

    public boolean isFriend(String userId) {
        if(friendsId.contains(userId)){
            return true;
        }
        return false;
    }
}
