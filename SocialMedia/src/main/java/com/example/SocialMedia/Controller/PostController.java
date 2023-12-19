package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Dto.CreateCommentRequestDto;
import com.example.SocialMedia.Dto.CreatePostDto;
import com.example.SocialMedia.Entity.Comment;
import com.example.SocialMedia.Entity.Post;
import com.example.SocialMedia.Service.CommentService;
import com.example.SocialMedia.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/socialmedia")
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    CommentService commentService;
    @PostMapping("/create/post")
    public ResponseEntity<Post> createPost(@RequestBody CreatePostDto createPostDto) {
        try {
            Post post = postService.createPost(createPostDto);
            return ResponseEntity.ok(post);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping("/addComment")
    public ResponseEntity<Comment> addComment(@RequestBody CreateCommentRequestDto createCommentRequestDto) {
        String postId= createCommentRequestDto.getPostId();
        String text= createCommentRequestDto.getComment();
        try {
            Comment comment = commentService.addComment(postId, text);
            return ResponseEntity.ok(comment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/api/posts/user/{ownerId}")
    public List<Post> getPostsForUser(@PathVariable String ownerId, @RequestParam(required = false) String viewerId) {
        return postService.getPostsForUser(ownerId, viewerId);
    }
}
