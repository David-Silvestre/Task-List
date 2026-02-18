package com.task_list.task_list.dto;

import com.task_list.task_list.constants.TaskUrgency;

import java.time.LocalDate;

public record CreateTaskDto(
        String name,
        String description,
        TaskUrgency taskUrgency,
        LocalDate deadline
) {}
