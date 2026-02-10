package com.example.demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.example.demo.repository.GroupRepository;

@Service
public class ImpactService {
    private final GroupRepository groupRepository;
    public ImpactService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Async
    public void countUsersInGroup(String groupName){
        try{
            long count = groupRepository.countUsersInGroup(groupName);
            Thread.sleep(3000); 
            System.out.println(" [ASYNC SERVICE] Number of users in group '" + groupName + "': " + count);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        catch(IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid group name: " + groupName);
        }
    }
}
