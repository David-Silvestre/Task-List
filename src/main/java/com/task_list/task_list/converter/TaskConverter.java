package com.task_list.task_list.converter;

import com.task_list.task_list.dto.TaskDetailsDto;
import com.task_list.task_list.entity.TaskEntity;

public class TaskConverter {

    public static TaskDetailsDto toTaskDetailsDto(TaskEntity taskEntity){
        return TaskDetailsDto.builder()
                .taskId(taskEntity.getId())
                .name(taskEntity.getName())
                .description(taskEntity.getDescription())
                .taskUrgency(taskEntity.getTaskUrgency())
                .deadline(taskEntity.getDeadline())
                .build();
    }
}
