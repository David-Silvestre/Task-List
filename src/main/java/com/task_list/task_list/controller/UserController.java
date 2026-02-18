package com.task_list.task_list.controller;

import com.task_list.task_list.constants.UserType;
import com.task_list.task_list.dto.CreateTaskDto;
import com.task_list.task_list.dto.CreateUserDto;
import com.task_list.task_list.dto.TaskDetailsDto;
import com.task_list.task_list.dto.UserDetailsDto;
import com.task_list.task_list.entity.UserEntity;
import com.task_list.task_list.service.TaskService;
import com.task_list.task_list.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method (GET, POST, DELETE, PATCH, PUT)
     *  GET - Get an element or list of stuff
     *  POST - Create new
     *  PUT - Update the full object
     *  PATCH - Update part of the object
     *  DELETE - Delete object (can be soft or full delete)
     * Path /cenas/cenas2
     * Headers
     * Body
     *
     * Method - Action to be performed
     * Path - Which resources?
     *
     * EXEMPLO
     *
     * GET /users
     * GET /users/{userId}
     * POST /users
     *
     * GET /users/{userId}/tasks
     * GET /users/{userId}/tasks/{taskId}
     */

    @GetMapping("/users")
    public List<UserDetailsDto> getUsers() {
        log.info("Request to get all users");
        return userService.getUsers();
    }

    @GetMapping("/users/{userId}")
    public UserDetailsDto getUserById(@PathVariable("userId") Long userId) {
        log.info("Request to get user with id {}", userId);
        return userService.getUserById(userId);
    }


    @GetMapping("/users/{userId}/tasks")
    public List<TaskDetailsDto> getTasksByUserId(@PathVariable("userId") Long userId) {
        log.info("Request to get user with id {}", userId);
        return userService.getTasksByUserId(userId);
    }

    @PostMapping("/users")
    public UserDetailsDto createUser(@RequestBody() CreateUserDto createUserDto) {
        log.info("Request to create new user: {}", createUserDto);
        return userService.createUser(createUserDto);
    }

    @PostMapping("/users/{userId}/tasks")
    public TaskDetailsDto createTaskForUser(@RequestBody()CreateTaskDto createTaskDto, @PathVariable("userId") Long userId) {
        log.info("Request to create new task for user: {}", userId);
        return userService.createTaskForUser(createTaskDto, userId);
    }

    @DeleteMapping("/users/{usersId}")
    public void deleteUser(@PathVariable Long userId){
        log.info("Request to create new task for user: {}", userId);
        userService.deleteUser(userId);
    }

    @PatchMapping("/users/{userId}/tasks/{tasksId}/assign")
    public TaskDetailsDto addTaskToUserById(@PathVariable Long userId, @PathVariable Long tasksId){
        log.info("Request to add task {} for user: {}", tasksId, userId);
        return userService.addTaskToUserById(userId, tasksId);
    }

    @PatchMapping("/users/{userId}/tasks/{tasksId}/remove")
    public TaskDetailsDto removeTaskFromUserById(@PathVariable Long userId, @PathVariable Long tasksId){
        log.info("Request to remove task {} for user: {}", tasksId, userId);
        return userService.removeTaskFromUserById(userId, tasksId);
    }
    
}
