package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Group;
import com.example.demo.service.GroupService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/groups")
public class GroupController {
    private final GroupService groupService;
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createGroup(@Valid @RequestBody Group group){
        Group newGroup = groupService.createGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGroup);
    }

    @PostMapping("/{groupName}/parent/{parentGroupName}")
    public ResponseEntity<?> addParentToGroup(@PathVariable String groupName, @PathVariable String parentGroupName){
        Group updatedGroup = groupService.addParentToGroup(groupName, parentGroupName);
        return ResponseEntity.status(HttpStatus.OK).body(updatedGroup);
    }

    @PostMapping("/{groupName}/resources/{resourceId}")
    public ResponseEntity<?> addResourceToGroup(@PathVariable String groupName, @PathVariable String resourceId){
        Group updatedGroup = groupService.addResourceToGroup(groupName, resourceId);
        return ResponseEntity.status(HttpStatus.OK).body(updatedGroup);
    }
}