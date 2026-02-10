package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import com.example.demo.entity.User;

public interface UserRepository extends Neo4jRepository<User, String> {
    Optional<User> findByUsername(String username);

    @Query("MATCH (u:User {username: $username})-[:MEMBER_OF]->(userGroup:Group) " +
           "-[:CHILD_OF*0..]->(parentGroup:Group) " +
           "-[:HAS_ACCESS]->(r:Resource {name: $resourceName}) " +
           "RETURN count(r) > 0")
    boolean hasAccessToResource(String username, String resourceName);
}
