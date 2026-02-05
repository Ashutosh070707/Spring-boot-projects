package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Group;
import com.example.demo.entity.Resource;
import com.example.demo.entity.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.ResourceRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final ResourceRepository resourceRepository;

    public UserService(UserRepository userRepository, GroupRepository groupRepository, ResourceRepository resourceRepository) {
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.resourceRepository = resourceRepository;
    }

    public User createUser(User user){
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("User already exists with this username"); 
        }
        return userRepository.save(user);
    }

    public Group createGroup(Group group){
        if(groupRepository.findByName(group.getName()).isPresent()){
            throw new RuntimeException("Group already exists with this name"); 
        }
        return groupRepository.save(group);
    }

    public Resource createResource(Resource resource){
        if(resourceRepository.findByName(resource.getName()).isPresent()){
            throw new RuntimeException("Resource already exists with this name"); 
        }
        return resourceRepository.save(resource);
    }

}
