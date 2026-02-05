package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;

import com.example.demo.entity.User;

public interface UserRepository extends Neo4jRepository<User, String> {
    Optional<User> findByUsername(String username);
}
