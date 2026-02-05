package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

@Node("Group")
public class Group {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id; 
    private String name;
    private String description;

    @Relationship(type="CHILD_OF", direction=Relationship.Direction.OUTGOING)
    private List<Group> groups = new ArrayList<>();
    @Relationship(type="HAS_ACCESS", direction=Relationship.Direction.OUTGOING)
    private List<Resource> resources = new ArrayList<>();

    public Group() {
    }

    public Group(String id, String name, String description, List<Group> groups, List<Resource> resources) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.groups = groups;
        this.resources = resources;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
    
}
