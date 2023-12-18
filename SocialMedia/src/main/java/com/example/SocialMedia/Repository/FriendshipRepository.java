package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Friendship;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendshipRepository extends MongoRepository<Friendship,String> {

    List<String> findFriendsByUserId(String userId);
}
