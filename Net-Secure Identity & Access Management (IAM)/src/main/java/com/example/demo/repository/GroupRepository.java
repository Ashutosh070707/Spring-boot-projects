package com.example.demo.repository;


import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Group;

public interface GroupRepository extends Neo4jRepository<Group, String> {
    Optional<Group> findByName(String name);

    @Query("match (start:Group {name: $startName}), (end:Group {name: $endName}) " + "match path = (start)-[:CHILD_OF*]->(end) " + "return count(path)>0")
    boolean isDescendent(@Param("startName") String startName, @Param("endName") String endName);
}
