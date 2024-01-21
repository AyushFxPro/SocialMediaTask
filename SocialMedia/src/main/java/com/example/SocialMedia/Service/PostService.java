package com.example.SocialMedia.Service;

import com.example.SocialMedia.Dto.CreatePostDto;
import com.example.SocialMedia.Dto.UserRequestDto;
import com.example.SocialMedia.Entity.Like;
import com.example.SocialMedia.Entity.Post;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Entity.UserProfile;
import com.example.SocialMedia.Enum.PrivacySetting;
import com.example.SocialMedia.Repository.*;
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
    @Autowired
    LikeRepository likeRepository;

    public Post createPost(CreatePostDto createPostDto) throws  IOException {
        Post post = new Post();
        post.setUserId(createPostDto.getUserId());
        post.setText(createPostDto.getText());
        post.setImage(createPostDto.getImage());
        post.setVideo(createPostDto.getVideo());
        post.setPrivacySetting(createPostDto.getPrivacySetting());
        Post savedpost=postRepository.save(post);
        //CreatePostDto createPostDto1=new CreatePostDto();
        //createPostDto1.setUserId();
//        UserProfile userProfile=userPofileRepository.findByEmailId(createPostDto.getUserId());
//        userProfile.getPosts().add(createPostDto);
//        userPofileRepository.save(userProfile);
//        if(optionalUserProfile.isPresent()){
//           UserProfile userProfile=optionalUserProfile.get();
//            userProfile.getPosts().add(createPostDto);
//            userPofileRepository.save(userProfile);
//        }
        User user=userRepository.findById(createPostDto.getUserId()).get();
        UserProfile userProfile=userPofileRepository.findByEmailId(user.getEmail());
        userProfile.getPosts().add(createPostDto);
        userPofileRepository.save(userProfile);

        return savedpost;
    }

    public List<Post> getPostsForUser(String ownerId, String viewerId) {

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

    public Like addLike(String postId, String userId) {

        Like like = new Like();
        like.setPostId(postId);
        like.setUserId(userId);
        User user=userRepository.findById(userId).get();
        UserRequestDto userRequestDto=new UserRequestDto();
        userRequestDto.setUsername(user.getUsername());
        userRequestDto.setEmail(user.getEmail());
        //get the post and then add like in it
        Post post=postRepository.findById(postId).get();
        post.getLikes().add(userRequestDto);
        postRepository.save(post);

        // Save the like in the repository
        return likeRepository.save(like);
    }

}
