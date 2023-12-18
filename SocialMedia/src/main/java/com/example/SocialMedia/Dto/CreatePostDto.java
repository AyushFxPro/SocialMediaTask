package com.example.SocialMedia.Dto;

import com.example.SocialMedia.Enum.PrivacySetting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreatePostDto {
    private String userId;
    private String text;
    private String image;
    private String video;
    private PrivacySetting privacySetting;
}
