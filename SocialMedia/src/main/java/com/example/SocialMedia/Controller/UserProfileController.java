package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Dto.UserProfileDto;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Entity.UserProfile;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Service.UserProfileService;
import com.example.SocialMedia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipal;
import java.util.Optional;

@RestController
@RequestMapping("/Socialmedia/userprofile")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserProfile> createUserProfile(
            @RequestBody UserProfileDto userProfileDto
            ) {

        Optional<User> optionalUser = userRepository.findByEmail(userProfileDto.getEmail());
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            UserProfile userProfile = userProfileService.createUserProfile(userProfileDto, user);
            return ResponseEntity.ok(userProfile);
        }
        else {
            throw new RuntimeException("User not found");
        }
    }
}
