package com.example.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Group;
import com.example.demo.entity.Resource;
import com.example.demo.entity.User;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterReturning("execution(* com.example.demo.service.UserService.createUser(..))")
    public void logUserCreation(JoinPoint joinPoint) {
        
        Object[] args = joinPoint.getArgs();
        User user = (User) args[0];

        logger.info(" [AUDIT LOG] SUCCESS: User '{}' has been created", user.getUsername());
    }

    @AfterReturning("execution(* com.example.demo.service.GroupService.createGroup(..))")
    public void logGroupCreation(JoinPoint joinPoint) {
        
        Object[] args = joinPoint.getArgs();
        Group group = (Group) args[0];

        logger.info(" [AUDIT LOG] SUCCESS: Group '{}' has been created", group.getName());
    }

    @AfterReturning("execution(* com.example.demo.service.ResourceService.createResource(..))")
    public void logResourceCreation(JoinPoint joinPoint) {
        
        Object[] args = joinPoint.getArgs();
        Resource resource = (Resource) args[0];

        logger.info(" [AUDIT LOG] SUCCESS: Resource '{}' has been created", resource.getName());
    }

    @AfterReturning("execution(* com.example.demo.service.GroupService.addResourceToGroup(..))")
    public void logResourceMembership(JoinPoint joinPoint) {
        
        Object[] args = joinPoint.getArgs();
        String groupName = (String) args[0];
        String resourceName = (String) args[1];

        logger.info(" [AUDIT LOG] SUCCESS: Resource '{}' has been added to Group '{}'", resourceName, groupName);
    }


    @AfterReturning("execution(* com.example.demo.service.UserService.addUserToGroup(..))")
    public void logGroupMembership(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        String username = (String) args[0];
        String groupName = (String) args[1];

        logger.info(" [AUDIT LOG] SUCCESS: User '{}' has been added to Group '{}'", username, groupName);
    }

    @AfterReturning("execution(* com.example.demo.service.GroupService.addParentToGroup(..))")
    public void logAddingParent(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String childGroup = (String) args[0];
        String parentGroup = (String) args[1];

        logger.info(" [AUDIT LOG] SUCCESS: Parent Group '{}' has been added to Child Group '{}' parents list", parentGroup, childGroup);
    }

    @Before("execution(* com.example.demo.service.ImpactService.countUsersInGroup(..))")
    public void logCountUsersInGroup(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String groupName = (String) args[0];

        logger.info(" [AUDIT LOG] TRIGGERED: Async counting for Group '{}'", groupName);
    }

} 