package com.example.SocialMedia.Service;

import com.example.SocialMedia.Dto.UserProfileDto;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Entity.UserProfile;
import com.example.SocialMedia.Repository.UserPofileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {
    @Autowired
    UserPofileRepository userPofileRepository;
    public UserProfile createUserProfile(UserProfileDto userProfileDto, User user) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUser(user);
        userProfile.setEmailId(userProfileDto.getEmail());
        userProfile.setBio(userProfileDto.getBio());
        userProfile.setProfilePicture(userProfileDto.getProfilePicture());
        UserProfile savedUserProfile=userPofileRepository.save(userProfile);

        return savedUserProfile;
    }
}
