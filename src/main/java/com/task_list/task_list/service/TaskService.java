package com.task_list.task_list.service;

import com.task_list.task_list.converter.TaskConverter;
import com.task_list.task_list.dto.CreateTaskDto;
import com.task_list.task_list.dto.TaskDetailsDto;
import com.task_list.task_list.entity.TaskEntity;
import com.task_list.task_list.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@AllArgsConstructor
@Service
public class TaskService {
    private TaskRepository taskRepository;

    public List<TaskDetailsDto> getTasks(){
        return taskRepository.findAll().stream()
            .map(TaskConverter::toTaskDetailsDto).toList();
    }

    public TaskDetailsDto getTaskById(final Long taskId){
        return taskRepository.findById(taskId)
                .map(TaskConverter::toTaskDetailsDto)
                .orElseThrow(() ->{
                    log.error("Task with id {} not found", taskId);
                    return new NoSuchElementException();
                });
    }

    public TaskDetailsDto createTask(CreateTaskDto createTaskDto){
        return TaskConverter.toTaskDetailsDto(
                taskRepository.saveAndFlush(TaskEntity.builder()
                        .name(createTaskDto.name())
                        .description(createTaskDto.description())
                        .taskUrgency(createTaskDto.taskUrgency())
                        .deadline(createTaskDto.deadline())
                        .build()));
    }

    @Transactional
    public void deleteTaskById(Long taskId){
        TaskEntity task = taskRepository.findById(taskId)
                .orElseThrow(()->{
                    log.error("Task with id {} not found", taskId);
                    return new NoSuchElementException();
                });
        task.setActive(false);
    }
}
