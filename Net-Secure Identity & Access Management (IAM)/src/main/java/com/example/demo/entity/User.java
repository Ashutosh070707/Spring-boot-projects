package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import com.example.demo.enums.UserRole;
import com.example.demo.enums.UserStatus;

@Node("User")
public class User {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id; 
    private String username;
    private String email;
    private UserStatus status;
    private UserRole role;

    @Relationship(type = "MEMBER_OF", direction=Relationship.Direction.OUTGOING)
    private List<Group> groups = new ArrayList<>();


    public User() {
    }
    public User(String id, String username, String email, UserStatus status, UserRole role, List<Group> groups) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.status = status;
        this.role = role;
        this.groups = groups;
    }
    public String getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public UserStatus getStatus() {
        return status;
    }
    public UserRole getRole() {
        return role;
    }
    public List<Group> getGroups() {
        return groups;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setStatus(UserStatus status) {
        this.status = status;
    }
    public void setRole(UserRole role) {
        this.role = role;
    }
    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
