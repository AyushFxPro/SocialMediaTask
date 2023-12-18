package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Dto.LoginRequestDto;
import com.example.SocialMedia.Dto.UserRequestDto;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Socialmedia/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRequestDto userDto) {
        User newUser = userService.registerUser(userDto);
        return ResponseEntity.ok(newUser);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDto loginRequest) {
        try {
            userService.loginUser(loginRequest);
            return ResponseEntity.ok("Login successful");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UserRequestDto userDto, @PathVariable String userId) {
        User updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
