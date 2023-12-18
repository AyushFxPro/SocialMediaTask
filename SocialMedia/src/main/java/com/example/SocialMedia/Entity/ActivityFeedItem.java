package com.example.SocialMedia.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "activityFeedItems")
public class ActivityFeedItem {
    private String content;
    private LocalDateTime timestamp;
    private String type;

    public ActivityFeedItem(String content, LocalDateTime timestamp) {
    }
}
