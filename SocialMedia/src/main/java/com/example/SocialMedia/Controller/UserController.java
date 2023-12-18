package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Dto.UserRequestDto;
import com.example.SocialMedia.Entity.User;
import com.example.SocialMedia.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.access.prepost.PreAuthorize;
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

    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody UserRequestDto userDto, @PathVariable String userId) {
        User updatedUser = userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getUser")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
