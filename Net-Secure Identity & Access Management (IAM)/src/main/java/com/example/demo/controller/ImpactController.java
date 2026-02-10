package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.ImpactService;

@RestController
@RequestMapping("/api/impact")
public class ImpactController {
    private final ImpactService impactService;
    public ImpactController(ImpactService impactService) {
        this.impactService = impactService;
    }

    @GetMapping("/count-users/{groupName}")
    public ResponseEntity<?> countUsersInGroup(@PathVariable String groupName) throws InterruptedException {
        impactService.countUsersInGroup(groupName);
        return ResponseEntity.ok("User count is being calculated. Check console for results.");
    }
}
