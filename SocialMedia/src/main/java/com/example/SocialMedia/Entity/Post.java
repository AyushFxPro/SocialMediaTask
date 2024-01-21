package com.example.SocialMedia.Entity;

import com.example.SocialMedia.Dto.UserRequestDto;
import com.example.SocialMedia.Enum.PrivacySetting;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String userId;
    private String text;
    private String image;
    private String video;
    private LocalDateTime timestamp;
    private List<String> comments = new ArrayList<>();
    private List<UserRequestDto> likes=new ArrayList<>();
    private PrivacySetting privacySetting;
}
