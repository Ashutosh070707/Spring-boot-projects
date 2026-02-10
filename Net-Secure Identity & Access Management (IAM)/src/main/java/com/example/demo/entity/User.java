package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
// import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import com.example.demo.enums.UserRole;
import com.example.demo.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

// Import these (jakarta.* for Spring Boot 3+)
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Node("User")
public class User {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id;
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    @NotNull(message = "Status is required")
    private UserStatus status;
    @NotNull(message = "Role is required")
    private UserRole role;

    @Relationship(type = "MEMBER_OF", direction=Relationship.Direction.OUTGOING)
    private List<Group> groups = new ArrayList<>();


    public User() {
    }
    public User(String id, String name, String username, String password, String email, UserStatus status, UserRole role, List<Group> groups) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.role = role;
        this.groups = groups;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
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
    public void setName(String name) {
        this.name = name;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
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
