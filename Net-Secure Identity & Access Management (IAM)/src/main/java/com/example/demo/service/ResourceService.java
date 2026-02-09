package com.example.demo.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Resource;
import com.example.demo.repository.ResourceRepository;

@Service
public class ResourceService {
    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public Resource createResource(Resource resource) {
        try {
            return resourceRepository.save(resource);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Resource already exists.");
        }
    }
}