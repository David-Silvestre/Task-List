package com.task_list.task_list.repository;

import com.task_list.task_list.constants.UserType;
import com.task_list.task_list.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findAllByUserType(UserType userType);
}
