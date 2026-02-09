package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import com.example.demo.enums.ResourceSensitivity;
import com.example.demo.enums.ResourceType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Node("Resource")
public class Resource {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id;
    @NotBlank(message = "Resource name is required")
    private String name;
    @NotNull(message = "Resource type is required")
    private ResourceType type;
    @NotNull(message = "Resource sensitivity is required")
    private ResourceSensitivity sensitivity;

    public Resource() {
    }

    public Resource(String id, String name, ResourceType type, ResourceSensitivity sensitivity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.sensitivity = sensitivity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ResourceType getType() {
        return type;
    }

    public ResourceSensitivity getSensitivity() {
        return sensitivity;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public void setSensitivity(ResourceSensitivity sensitivity) {
        this.sensitivity = sensitivity;
    }

   
}
