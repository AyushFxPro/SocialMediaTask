package com.example.SocialMedia.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileDto {
    //private String id;
    private String email;
    private String bio;
    private String profilePicture;
}
