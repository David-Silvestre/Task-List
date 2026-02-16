package com.task_list.task_list.dto;

import com.task_list.task_list.constants.UserType;

public record CreateUserDto(
        String name,
        UserType userType
) {}
