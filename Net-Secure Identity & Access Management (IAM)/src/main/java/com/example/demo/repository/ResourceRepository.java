package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.entity.Resource;

public interface ResourceRepository extends Neo4jRepository<Resource, String> {
    Optional<Resource> findByName(String name);
}
