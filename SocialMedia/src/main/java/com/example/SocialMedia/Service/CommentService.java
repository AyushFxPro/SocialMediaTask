package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Comment;
import com.example.SocialMedia.Entity.Post;
import com.example.SocialMedia.Repository.CommentRepository;
import com.example.SocialMedia.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    CommentRepository commentRepository;
    public Comment addComment(String postId, String text) {
        Optional<Post> postOptional = postRepository.findById(postId);

        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            Comment comment = new Comment();
            comment.setComment(text);
            comment.setPostId(post.getId());
            post.getComments().add(comment.getComment());
            postRepository.save(post);
            return commentRepository.save(comment);
        } else {
            throw new RuntimeException("Post not found");
        }
    }
}
