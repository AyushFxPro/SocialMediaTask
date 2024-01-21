package com.example.SocialMedia.Service;

import com.example.SocialMedia.Dto.UserProfileDto;
import com.example.SocialMedia.Entity.Friendship;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Entity.UserProfile;
import com.example.SocialMedia.Enum.FriendshipStatus;
import com.example.SocialMedia.Repository.FriendshipRepository;
import com.example.SocialMedia.Repository.UserPofileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class
FriendshipService {
    @Autowired
    private FriendshipRepository friendshipRepository;
    @Autowired
    private UserPofileRepository userPofileRepository;

    public void sendFriendRequest(User user, User friend) {
        Friendship friendship = new Friendship();
        friendship.setUserId(user.getId());
        friendship.setFriendId(friend.getId());
        friendship.setStatus(FriendshipStatus.PENDING);
        friendshipRepository.save(friendship);

        // add ths request to waiting list of friend Profile
        UserProfile userProfile=userPofileRepository.findByEmailId(user.getEmail());
        UserProfileDto userProfileDto=new UserProfileDto();
        userProfileDto.setEmail(userProfile.getEmailId());
        userProfileDto.setProfilePicture(userProfile.getProfilePicture());
        userProfileDto.setProfilePicture(userProfile.getProfilePicture());

        UserProfile friendsProfile=userPofileRepository.findByEmailId(friend.getEmail());
        friendsProfile.getWaitingRequests().add(userProfileDto);
        userPofileRepository.save(friendsProfile);
//        userProfile.getWaitingRequests().add(userProfile);
//        userPofileRepository.save(userProfile);
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
