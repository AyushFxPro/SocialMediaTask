package com.example.SocialMedia.Entity;

import com.example.SocialMedia.Dto.CreatePostDto;
import com.example.SocialMedia.Dto.UserProfileDto;
import com.example.SocialMedia.Dto.UserRequestDto;
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
    private String emailId;

    private String bio;
    private String profilePicture;
    @DBRef
    private List<Repost> reposts = new ArrayList<>();
    private List<CreatePostDto> posts=new ArrayList<>();
    //@DBRef(lazy = true)
    private List<String> friends=new ArrayList<>();
    private List<UserProfileDto> waitingRequests=new ArrayList<>();



//    @DBRef(lazy = true)
    private List<UserRequestDto> followers=new ArrayList<>();

    public boolean isFriend(String userId) {
        if(friends.contains(userId)){
            return true;
        }
        return false;
    }
}
