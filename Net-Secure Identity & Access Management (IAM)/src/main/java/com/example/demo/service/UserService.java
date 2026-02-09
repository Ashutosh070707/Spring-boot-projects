package com.example.demo.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Group;
import com.example.demo.entity.User;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    public UserService(UserRepository userRepository, GroupRepository groupRepository) {
        this.userRepository = userRepository;  
        this.groupRepository = groupRepository;
    }

    public User createUser(User user){
        try{
            return userRepository.save(user);
        }
        catch(DataIntegrityViolationException e){
            throw new IllegalArgumentException("User creation failed: Username or Email already exists.");
        }
    }

    @Transactional
    public User addUserToGroup(String username, String groupname){
        User user = userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("No user found with username: " + username));
        Group group = groupRepository.findByName(groupname).orElseThrow(() -> new IllegalArgumentException("No group found with name: " + groupname));

        if(user.getGroups().stream().anyMatch((g) -> g.getId().equals(group.getId()))){
            throw new IllegalArgumentException("User is already a member of the group.");
        }
        user.getGroups().add(group);
        return userRepository.save(user);
    }
}
