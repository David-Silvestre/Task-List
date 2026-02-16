package com.task_list.task_list.converter;

import com.task_list.task_list.dto.UserDetailsDto;
import com.task_list.task_list.entity.UserEntity;

public class UserConverter {

    public static UserDetailsDto toUserDetailsDto(UserEntity userEntity) {
        return UserDetailsDto.builder()
                .userId(userEntity.getId())
                .name(userEntity.getName())
                .userType(userEntity.getUserType())
                .build();
    }
}
