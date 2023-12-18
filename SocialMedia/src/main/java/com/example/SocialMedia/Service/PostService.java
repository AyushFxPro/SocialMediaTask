package com.example.SocialMedia.Service;

import com.example.SocialMedia.Dto.CreatePostDto;
import com.example.SocialMedia.Entity.Post;
import com.example.SocialMedia.Entity.UserProfile;
import com.example.SocialMedia.Enum.PrivacySetting;
import com.example.SocialMedia.Repository.FriendshipRepository;
import com.example.SocialMedia.Repository.PostRepository;
import com.example.SocialMedia.Repository.UserPofileRepository;
import com.example.SocialMedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserPofileRepository userPofileRepository;
    @Autowired
    FriendshipRepository friendshipRepository;

    public Post createPost(CreatePostDto createPostDto) throws  IOException {
        Post post = new Post();
        //createPostDto.getUserId()
        post.setUserId(createPostDto.getUserId());
        post.setText(createPostDto.getText());
        post.setImage(createPostDto.getImage());
        post.setVideo(createPostDto.getVideo());
        post.setPrivacySetting(createPostDto.getPrivacySetting());
        Optional<UserProfile> optionalUserProfile=userPofileRepository.findById(createPostDto.getUserId());
        if(optionalUserProfile.isPresent()){
           UserProfile userProfile=optionalUserProfile.get();
            userProfile.getPosts().add(post);
            userPofileRepository.save(userProfile);
        }

        return postRepository.save(post);
    }

    public List<Post> getPostsForUser(String ownerId, String viewerId) {
//        Optional<UserProfile> optionalUserProfile=userPofileRepository.findById(ownerId);
//        if(optionalUserProfile.isPresent()){
//            UserProfile userProfile=optionalUserProfile.get();
//        }

        Optional<UserProfile> optionalUserProfile = userPofileRepository.findById(ownerId);

        UserProfile owner = optionalUserProfile
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + ownerId));

        Optional<UserProfile> viewerOptional = Optional.ofNullable(viewerId)
                .flatMap(userId -> userPofileRepository.findById(userId));

        UserProfile viewer = viewerOptional.orElse(null);

        List<Post> allPosts = postRepository.findByUserId(ownerId);

        return allPosts.stream()
                .filter(post -> isPostVisible(post, viewer))
                .collect(Collectors.toList());
    }
    private boolean isPostVisible(Post post, UserProfile viewer) {
        if (post.getPrivacySetting().equals("PUBLIC")) {
            // Public posts are visible to all
            return true;
        } else {
            // Private posts are visible to friends only
            return viewer != null && viewer.isFriend(post.getUserId());
        }
    }

}
