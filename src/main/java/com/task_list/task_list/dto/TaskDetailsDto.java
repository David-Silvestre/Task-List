package com.task_list.task_list.dto;

import com.task_list.task_list.constants.TaskUrgency;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record TaskDetailsDto (
        Long taskId,
        String name,
        String description,
        TaskUrgency taskUrgency,
        LocalDate deadline
){}

