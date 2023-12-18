package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.Share;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShareRepository extends MongoRepository<Share,String> {
    List<Share> findByUserId(String userId);
}
