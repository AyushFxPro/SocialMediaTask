package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPofileRepository extends MongoRepository<UserProfile,String> {

}
