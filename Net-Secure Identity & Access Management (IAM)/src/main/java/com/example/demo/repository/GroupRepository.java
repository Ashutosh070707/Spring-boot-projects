package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.entity.Group;

public interface GroupRepository extends Neo4jRepository<Group, String> {
    Optional<Group> findByName(String name);
}
