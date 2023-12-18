package com.example.SocialMedia.Controller;

import com.example.SocialMedia.Entity.Message;
import com.example.SocialMedia.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/user/{senderId}/conversation/{receiverId}")
    public ResponseEntity<List<Message>> getMessagesBetweenUsers(@PathVariable String senderId, @PathVariable String receiverId) {
        List<Message> messages = messageService.getMessagesBetweenUsers(senderId, receiverId);
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestParam String senderId, @RequestParam String receiverId, @RequestParam String content) {
        Message message = messageService.sendMessage(senderId, receiverId, content);
        return ResponseEntity.ok(message);
    }
}
