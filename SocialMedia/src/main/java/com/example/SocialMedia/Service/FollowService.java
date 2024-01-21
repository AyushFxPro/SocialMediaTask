package com.example.SocialMedia.Service;

import com.example.SocialMedia.Dto.UserRequestDto;
import com.example.SocialMedia.Entity.Follow;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Entity.UserProfile;
import com.example.SocialMedia.Repository.FollowRepository;
import com.example.SocialMedia.Repository.UserPofileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;
    @Autowired
    private UserPofileRepository userPofileRepository;

    public void follow(User follower, User followee) {
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowee(followee);
        followRepository.save(follow);
        UserProfile followeeProfile=userPofileRepository.findByEmailId(followee.getEmail());
//        followeeProfile.getFollowers().add(follower.getId());
//        userPofileRepository.save(followeeProfile);
        //creting followee profile dto
        UserRequestDto followerProfileDto=new UserRequestDto();
        followerProfileDto.setEmail(follower.getEmail());
        followerProfileDto.setUsername(follower.getUsername());
        followeeProfile.getFollowers().add(followerProfileDto);
        userPofileRepository.save(followeeProfile);
    }

    public void unfollow(String followId) {
        followRepository.deleteById(followId);
    }
}
