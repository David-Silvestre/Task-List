package com.task_list.task_list.service;

import com.task_list.task_list.converter.UserConverter;
import com.task_list.task_list.dto.CreateUserDto;
import com.task_list.task_list.dto.UserDetailsDto;
import com.task_list.task_list.entity.UserEntity;
import com.task_list.task_list.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

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

    public UserDetailsDto createUser(CreateUserDto createUserDto) {
        return UserConverter.toUserDetailsDto(
                userRepository.saveAndFlush(UserEntity.builder()
                        .name(createUserDto.name())
                        .userType(createUserDto.userType())
                        .build()));
    }
}
