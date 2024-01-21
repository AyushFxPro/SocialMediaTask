package com.example.SocialMedia.Repository;

import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Entity.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPofileRepository extends MongoRepository<UserProfile,String> {
    UserProfile findByEmailId(String email);
}
