package com.example.demo.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder; // Import this
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    } 
  
    @PostMapping("/{username}/groups/{groupName}")
    public ResponseEntity<?> addUserToGroup(@PathVariable String username, @PathVariable String groupName){
        User updatedUser = userService.addUserToGroup(username, groupName);
        return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
    }


    @GetMapping("/checkHasAccess")
    public ResponseEntity<?> checkAccess(
            @RequestParam String username, 
            @RequestParam String resource) {
        
        boolean hasAccess = userService.checkAccess(username, resource);
        
        return ResponseEntity.ok(Map.of(
            "username", username,
            "resource", resource,
            "access_granted", hasAccess
        ));
    }
}