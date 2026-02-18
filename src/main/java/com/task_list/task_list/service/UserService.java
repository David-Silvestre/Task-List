package com.task_list.task_list.service;

import com.task_list.task_list.converter.TaskConverter;
import com.task_list.task_list.converter.UserConverter;
import com.task_list.task_list.dto.CreateTaskDto;
import com.task_list.task_list.dto.CreateUserDto;
import com.task_list.task_list.dto.TaskDetailsDto;
import com.task_list.task_list.dto.UserDetailsDto;
import com.task_list.task_list.entity.TaskEntity;
import com.task_list.task_list.entity.UserEntity;
import com.task_list.task_list.repository.TaskRepository;
import com.task_list.task_list.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public List<UserDetailsDto> getUsers() {
        return userRepository.findAll().stream()
                .map(UserConverter::toUserDetailsDto)
                .toList();
    }

    public UserDetailsDto getUserById(final Long userId) {
        return userRepository.findById(userId)
                .map(UserConverter::toUserDetailsDto)
                .orElseThrow(() -> {
                    log.error("User with id {} not found", userId);
                    return new NoSuchElementException();
                });
    }

    public List<TaskDetailsDto> getTasksByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(UserEntity::getTaskList)
                .map(taskEntities -> taskEntities.stream()
                    .map(TaskConverter::toTaskDetailsDto)
                    .toList())
                .orElseThrow(() -> {
                    log.error("User with id {} not found", userId);
                    return new NoSuchElementException();
                });
    }

    public UserDetailsDto createUser(CreateUserDto createUserDto) {
        return UserConverter.toUserDetailsDto(
                userRepository.saveAndFlush(UserEntity.builder()
                        .name(createUserDto.name())
                        .userType(createUserDto.userType())
                        .build()));
    }

    public TaskDetailsDto createTaskForUser(CreateTaskDto createTaskDto, Long userId){
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->{
                    log.error("User with id {} not found", userId);
                    return new NoSuchElementException();
                });

        TaskEntity task = taskRepository.saveAndFlush(TaskEntity.builder()
                .name(createTaskDto.name())
                .description(createTaskDto.description())
                .taskUrgency(createTaskDto.taskUrgency())
                .deadline(createTaskDto.deadline())
                .user(user)
                .build());
        return TaskConverter.toTaskDetailsDto(task);
    }

    @Transactional
    public void deleteUser(Long userId){
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()-> {
                    log.error("User with id {} not found", userId);
                    return new NoSuchElementException();
                });

        user.getTaskList().forEach(taskEntity -> taskEntity.setActive(false));
        user.setActive(false);
    }

    @Transactional
    public TaskDetailsDto addTaskToUserById (Long userId, Long taskId){
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(()->{
            log.error("User with id {} not found", userId);
            return new NoSuchElementException();
        });
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(()-> {
            log.error("Task with id {} not found", taskId);
            return new NoSuchElementException();
        });

        task.setUser(user);
        return TaskConverter.toTaskDetailsDto(task);
    }

    @Transactional
    public TaskDetailsDto removeTaskFromUserById(Long userId, Long taskId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User with id {} not found", userId);
                    return new NoSuchElementException();
                });
        TaskEntity task = taskRepository.findById(taskId).orElseThrow(() -> {
            log.error("Task with id {} not found", taskId);
            return new NoSuchElementException();
        });

        if (!task.getUser().getId().equals(user.getId())) {
            log.error("Task with id {} not found", taskId);
            throw new IllegalStateException("Task does not belong to this user");
        }

        task.setUser(null);
        return TaskConverter.toTaskDetailsDto(task);
    }
}
