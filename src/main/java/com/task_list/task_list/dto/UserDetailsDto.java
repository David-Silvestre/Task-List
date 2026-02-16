package com.task_list.task_list.dto;

import com.task_list.task_list.constants.UserType;
import lombok.Builder;

@Builder
public record UserDetailsDto(
        Long userId,
        String name,
        UserType userType
) {
}
