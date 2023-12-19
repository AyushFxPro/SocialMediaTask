package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.Comment;
import com.example.SocialMedia.Entity.Like;
import com.example.SocialMedia.Entity.Share;
import com.example.SocialMedia.Repository.CommentRepository;
import com.example.SocialMedia.Repository.LikeRepository;
import com.example.SocialMedia.Repository.ShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserInteractionService {
    @Autowired
    LikeRepository likeRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    ShareRepository shareRepository;

    public List<Like> getUserLikes(String userId) {
        return likeRepository.findByUserId(userId);
    }

    public List<Comment> getUserComments(String userId) {
        return commentRepository.findByUserId(userId);
    }

    public List<Share> getUserShares(String userId) {
        return shareRepository.findByUserId(userId);
    }

    public void trackLike(String userId, String postId) {
        Like like = new Like();
        like.setUserId(userId);
        like.setPostId(postId);
        like.setTimestamp(LocalDateTime.now());
        likeRepository.save(like);
    }

    public void trackComment(String userId, String postId, String text) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setPostId(postId);
        comment.setComment(text);
        comment.setTimestamp(LocalDateTime.now());
        commentRepository.save(comment);
    }

    public void trackShare(String userId, String postId) {
        Share share = new Share();
        share.setUserId(userId);
        share.setPostId(postId);
        share.setTimestamp(LocalDateTime.now());
        shareRepository.save(share);
    }
}
