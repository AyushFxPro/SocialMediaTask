package com.example.SocialMedia.Service;

import com.example.SocialMedia.Entity.*;
import com.example.SocialMedia.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityFeedService {
    @Autowired
    FriendshipRepository friendshipRepository;

    @Autowired
    FollowRepository followRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    NotificationRepository notificationRepository;

    public List<ActivityFeedItem> getPersonalizedActivityFeed(String userId) {
        List<String> friendIds = friendshipRepository.findFriendsByUserId(userId);
        List<String> followedUserIds = followRepository.findFolloweesByFollowerId(userId);


        List<Post> friendPosts = postRepository.findByUserIdInOrderByTimestampDesc(friendIds);
        List<Post> followedUserPosts = postRepository.findByUserIdInOrderByTimestampDesc(followedUserIds);

        List<Like> likes = likeRepository.findByUserIdInOrderByTimestampDesc(friendIds);
        List<Comment> comments = commentRepository.findByUserIdInOrderByTimestampDesc(friendIds);

        // Combine and sort posts, likes, and comments
        List<ActivityFeedItem> activityFeed = mergeAndSortActivity(friendPosts, followedUserPosts, likes, comments);

        // Fetch and include friend requests and other notifications
        List<Notification> notifications = notificationRepository.findByUserIdOrderByTimestampDesc(userId);
        activityFeed.addAll(mapNotificationsToActivityFeedItems(notifications));

        // Sort the final activity feed by timestamp
        activityFeed.sort(Comparator.comparing(ActivityFeedItem::getTimestamp).reversed());

        return activityFeed;
    }

    private List<ActivityFeedItem> mergeAndSortActivity(List<Post> friendPosts, List<Post> followedUserPosts,
                                                        List<Like> likes, List<Comment> comments) {
        List<ActivityFeedItem> activityItems = new ArrayList<>();

        // Convert each entity type to ActivityFeedItem and add to the unified list
        activityItems.addAll(convertPostsToActivityFeedItems(friendPosts));
        activityItems.addAll(convertPostsToActivityFeedItems(followedUserPosts));
        activityItems.addAll(convertLikesToActivityFeedItems(likes));
        activityItems.addAll(convertCommentsToActivityFeedItems(comments));

        // Sort the unified list by timestamp in descending order
        activityItems.sort(Comparator.comparing(ActivityFeedItem::getTimestamp).reversed());
        return activityItems;
    }

    private List<ActivityFeedItem> convertPostsToActivityFeedItems(List<Post> posts) {
        return posts.stream()
                .map(post -> new ActivityFeedItem(post.getText(), post.getTimestamp(), "POST"))
                .collect(Collectors.toList());
    }

    private List<ActivityFeedItem> convertLikesToActivityFeedItems(List<Like> likes) {
        return likes.stream()
                .map(like -> new ActivityFeedItem("Liked a post", like.getTimestamp(), "LIKE"))
                .collect(Collectors.toList());
    }

    private List<ActivityFeedItem> convertCommentsToActivityFeedItems(List<Comment> comments) {
        return comments.stream()
                .map(comment -> new ActivityFeedItem(comment.getComment(), comment.getTimestamp(), "COMMENT"))
                .collect(Collectors.toList());
    }

    private List<ActivityFeedItem> mapNotificationsToActivityFeedItems(List<Notification> notifications) {

        List<ActivityFeedItem> activityItems = new ArrayList<>();
        for (Notification notification : notifications) {
            activityItems.add(new ActivityFeedItem(notification.getContent(), notification.getTimestamp()));
        }
        return activityItems;
    }

}
