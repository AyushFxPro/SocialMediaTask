package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Friendship;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Entity.UserProfile;
import com.example.SocialMedia.Enum.FriendshipStatus;
import com.example.SocialMedia.Repository.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipService {
    @Autowired
    private FriendshipRepository friendshipRepository;

    public void sendFriendRequest(User user, User friend) {
        Friendship friendship = new Friendship();
        friendship.setUser(user);
        friendship.setFriend(friend);
        friendship.setStatus(FriendshipStatus.PENDING);
        friendshipRepository.save(friendship);
    }

    public void acceptFriendRequest(String friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new RuntimeException("Friendship not found"));
        friendship.setStatus(FriendshipStatus.ACCEPTED);
        friendshipRepository.save(friendship);
    }

    public void rejectFriendRequest(String friendshipId) {
        Friendship friendship = friendshipRepository.findById(friendshipId)
                .orElseThrow(() -> new RuntimeException("Friendship not found"));
        friendship.setStatus(FriendshipStatus.REJECTED);
        friendshipRepository.save(friendship);
    }
}
