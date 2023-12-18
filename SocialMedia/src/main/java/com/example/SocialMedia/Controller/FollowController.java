package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/follows")
public class FollowController {
    @Autowired
    FollowService followService;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/follow/{followerId}/{followeeId}")
    public ResponseEntity<String> followUser(@RequestParam String followerId, @RequestParam String followeeId) {
        // Get users by their IDs
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + followerId));

        User followee = userRepository.findById(followeeId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + followeeId));

        // Follow user
        followService.follow(follower, followee);
        return ResponseEntity.ok("User followed successfully");
    }

    @PostMapping("/unfollow")
    public ResponseEntity<String> unfollowUser(@RequestParam String followId) {
        followService.unfollow(followId);
        return ResponseEntity.ok("User unfollowed");
    }
}
