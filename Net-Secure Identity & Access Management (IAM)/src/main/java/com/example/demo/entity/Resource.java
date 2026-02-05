package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import com.example.demo.enums.ResourceSensitivity;
import com.example.demo.enums.ResourceType;

@Node("Resource")
public class Resource {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    private String id;
    private String name;
    private ResourceType type;
    private ResourceSensitivity senstivity;

    public Resource() {
    }

    public Resource(String id, String name, ResourceType type, ResourceSensitivity senstivity) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.senstivity = senstivity;
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

    public ResourceSensitivity getSenstivity() {
        return senstivity;
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

    public void setSenstivity(ResourceSensitivity senstivity) {
        this.senstivity = senstivity;
    }

   
}
