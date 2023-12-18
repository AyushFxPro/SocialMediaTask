package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Post;
import com.example.SocialMedia.Entity.Repost;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.PostRepository;
import com.example.SocialMedia.Repository.RepostRepository;
import com.example.SocialMedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RepostService {
    @Autowired
    RepostRepository repostRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public Repost repost(String postId, String userId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (postOptional.isPresent() && userOptional.isPresent()) {
            Post originalPost = postOptional.get();
            User user = userOptional.get();

            // Check if the user has already reposted this post
            Optional<Repost> existingRepost = repostRepository.findByOriginalPostIdAndUserId(postId, userId);
            if (existingRepost.isPresent()) {
                throw new RuntimeException("Post has already been reposted by the user.");
            }

            // Create a new repost
            Repost repost = new Repost();
            repost.setOriginalPost(originalPost);
            repostRepository.save(repost);

            // Add the repost to the user's reposts
            user.getUserProfile().getReposts().add(repost);
            userRepository.save(user);

            return repost;
        } else {
            throw new RuntimeException("Post or user not found.");
        }
    }
}
