package com.example.demo.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Group;
import com.example.demo.entity.Resource;
import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.ResourceRepository;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final ResourceRepository resourceRepository;

    public GroupService(GroupRepository groupRepository, ResourceRepository resourceRepository) {
        this.groupRepository = groupRepository;
        this.resourceRepository = resourceRepository;
    }

    public Group createGroup(Group group) {
        try {
            return groupRepository.save(group);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Group creation failed: A group with this name already exists.");
        }
    }

    @Transactional
    public Group addParentToGroup(String groupName, String parentGroupName) {
        Group group = groupRepository.findByName(groupName)
                .orElseThrow(() -> new IllegalArgumentException("No group found with name: " + groupName));
        Group parentGroup = groupRepository.findByName(parentGroupName)
                .orElseThrow(() -> new IllegalArgumentException("No group found with name: " + parentGroupName));

        if (group.getParents().stream().anyMatch((g) -> g.getId().equals(parentGroup.getId()))) {
            throw new IllegalArgumentException("The specified parent group is already a parent of the group.");
        }

        boolean isCycle = groupRepository.isDescendent(parentGroupName, groupName);
        if (isCycle) {
            throw new IllegalArgumentException("Cyclic dependency detected: Group '" + parentGroupName +
                    "' is already a descendant of '" + groupName + "'.");
        }
        group.getParents().add(parentGroup);
        return groupRepository.save(group);
    }

    @Transactional
    public Group addResourceToGroup(String groupName, String resourceId) {
        Group group = groupRepository.findByName(groupName)
                .orElseThrow(() -> new IllegalArgumentException("No group found with name: " + groupName));
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new IllegalArgumentException("No resource found with id: " + resourceId));

        if (group.getResources().stream().anyMatch((r) -> r.getId().equals(resource.getId()))) {
            throw new IllegalArgumentException("The specified resource is already associated with the group.");
        }
        group.getResources().add(resource);
        return groupRepository.save(group);
    }
}
