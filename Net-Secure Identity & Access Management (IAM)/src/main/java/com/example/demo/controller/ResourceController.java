package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Resource;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final UserService userService;
    public ResourceController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createResource(@RequestBody Resource resource){
        Resource newResource = userService.createResource(resource);
        return ResponseEntity.status(HttpStatus.CREATED).body(newResource);
    }
}
