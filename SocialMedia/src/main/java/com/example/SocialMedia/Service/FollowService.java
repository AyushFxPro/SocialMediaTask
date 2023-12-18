package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Follow;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    @Autowired
    private FollowRepository followRepository;

    public void follow(User follower, User followee) {
        Follow follow = new Follow();
        follow.setFollower(follower);
        follow.setFollowee(followee);
        followRepository.save(follow);
    }

    public void unfollow(String followId) {
        followRepository.deleteById(followId);
    }
}
