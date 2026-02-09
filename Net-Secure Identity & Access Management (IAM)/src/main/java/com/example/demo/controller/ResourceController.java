package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Resource;
import com.example.demo.service.ResourceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {
    private final ResourceService resourceService;
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createResource(@Valid @RequestBody Resource resource){
        Resource newResource = resourceService.createResource(resource);
        return ResponseEntity.status(HttpStatus.CREATED).body(newResource);
    }
}
