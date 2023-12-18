package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.UserRepository;
import com.example.SocialMedia.Service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/friendships")
public class FriendshipController {
    @Autowired
    FriendshipService friendshipService;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/request/{userId}/{friendId}")
    public ResponseEntity<String> sendFriendRequest(@RequestParam String userId, @RequestParam String friendId) {
        // Get users by their IDs
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        User friend = userRepository.findById(friendId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + friendId));

        friendshipService.sendFriendRequest(user, friend);
        return ResponseEntity.ok("Friend request sent successfully");
    }

    @PostMapping("/accept/{friendshipId}")
    public ResponseEntity<String> acceptFriendRequest(@RequestParam String friendshipId) {
        friendshipService.acceptFriendRequest(friendshipId);
        return ResponseEntity.ok("Friend request accepted");
    }

    @PostMapping("/reject/{friendshipId}")
    public ResponseEntity<String> rejectFriendRequest(@RequestParam String friendshipId) {
        friendshipService.rejectFriendRequest(friendshipId);
        return ResponseEntity.ok("Friend request rejected");
    }

}
