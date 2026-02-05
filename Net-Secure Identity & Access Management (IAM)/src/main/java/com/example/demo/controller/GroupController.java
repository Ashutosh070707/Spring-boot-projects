package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Group;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final UserService userService;
    public GroupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createGroup(@RequestBody Group group){
        Group newGroup = userService.createGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGroup);
    }
}
