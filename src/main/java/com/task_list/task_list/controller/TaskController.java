package com.task_list.task_list.controller;

import com.task_list.task_list.dto.CreateTaskDto;
import com.task_list.task_list.dto.CreateUserDto;
import com.task_list.task_list.dto.TaskDetailsDto;
import com.task_list.task_list.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){this.taskService = taskService;}

    @GetMapping("/tasks")
    public List<TaskDetailsDto> getTasks(){
        log.info("Request to get all tasks");
        return taskService.getTasks();
    }

    @GetMapping("/tasks/{taskId}")
    public TaskDetailsDto getTaskById(@PathVariable("taskId") Long taskId) {
        log.info("Request to get user with id {}", taskId);
        return taskService.getTaskById(taskId);
    }

    @PostMapping("/tasks")
    public TaskDetailsDto createTask(@RequestBody() CreateTaskDto createTaskDto) {
        log.info("Request to create new user: {}", createTaskDto);
        return taskService.createTask(createTaskDto);
    }

    @DeleteMapping("/tasks/{taskId}")
    public void deleteTaskById(@PathVariable Long taskId){
        taskService.deleteTaskById(taskId);
    }
}
