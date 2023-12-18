package com.example.SocialMedia.Service;

import com.example.SocialMedia.Dto.LoginRequestDto;
import com.example.SocialMedia.Dto.UserRequestDto;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User registerUser(UserRequestDto userDto) {
        // Check if the username or email is already taken
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new RuntimeException("Username is already taken");
        }

        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new RuntimeException("Email is already taken");
        }

        // Create a new user entity
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        newUser.setRoles(userDto.getRoles());
        newUser.setEnabled(true);
        // newUser.setRoles(Ad);

        // Save the user in the repository
        return userRepository.save(newUser);
    }

    public User updateUser(UserRequestDto userDto, String userId) {
        // Check if the user with the given ID exists
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Update the user details
        existingUser.setUsername(userDto.getUsername());
        existingUser.setEmail(userDto.getEmail());
        existingUser.setPassword(userDto.getPassword());

        // Save the updated user in the repository
        return userRepository.save(existingUser);
    }

    public User getUserById(String userId) {
        // Retrieve the user by ID
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    public void deleteUser(String userId) {
        // Check if the user with the given ID exists
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Delete the user from the repository
        userRepository.delete(existingUser);
    }

    public List<User> getAllUsers() {
        // Retrieve all users from the repository
        return userRepository.findAll();
    }

    public void loginUser(LoginRequestDto loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername());
                if (user==null){
                    throw new RuntimeException("Invalid username or password");
                }

        if (loginRequest.getPassword()!= user.getPassword()) {
            throw new RuntimeException("Invalid username or password");
        }
    }
}
