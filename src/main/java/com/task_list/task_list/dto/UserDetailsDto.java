package com.task_list.task_list.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task_list.task_list.constants.UserType;
import lombok.Builder;

import java.util.List;

@Builder
public record UserDetailsDto(
        Long userId,
        String name,
        UserType userType
) {}
