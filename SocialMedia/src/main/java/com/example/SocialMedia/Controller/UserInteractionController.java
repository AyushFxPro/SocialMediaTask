package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Entity.Comment;
import com.example.SocialMedia.Entity.Like;
import com.example.SocialMedia.Entity.Share;
import com.example.SocialMedia.Service.UserInteractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserInteractionController {
    @Autowired
    UserInteractionService userInteractionService;

    @GetMapping("/likes/{userId}")
    public ResponseEntity<List<Like>> getUserLikes(@PathVariable String userId) {
        List<Like> likes = userInteractionService.getUserLikes(userId);
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/comments/{userId}")
    public ResponseEntity<List<Comment>> getUserComments(@PathVariable String userId) {
        List<Comment> comments = userInteractionService.getUserComments(userId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/shares/{userId}")
    public ResponseEntity<List<Share>> getUserShares(@PathVariable String userId) {
        List<Share> shares = userInteractionService.getUserShares(userId);
        return ResponseEntity.ok(shares);
    }

    @PostMapping("/like")
    public ResponseEntity<String> trackLike(@RequestParam String userId, @RequestParam String postId) {
        userInteractionService.trackLike(userId, postId);
        return ResponseEntity.ok("Like tracked successfully");
    }

    @PostMapping("/comment")
    public ResponseEntity<String> trackComment(@RequestParam String userId, @RequestParam String postId, @RequestParam String text) {
        userInteractionService.trackComment(userId, postId, text);
        return ResponseEntity.ok("Comment tracked successfully");
    }

    @PostMapping("/share")
    public ResponseEntity<String> trackShare(@RequestParam String userId, @RequestParam String postId) {
        userInteractionService.trackShare(userId, postId);
        return ResponseEntity.ok("Share tracked successfully");
    }
}
