package com.task_list.task_list.entity;

import com.task_list.task_list.constants.UserType;
import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "Users")
@SQLDelete(sql = "UPDATE tasks SET active = false WHERE id=?")
@SQLRestriction("active = true")
public class UserEntity extends BaseEntity{

    private String name;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaskEntity> taskList;

    @CreationTimestamp(source = SourceType.DB)
    private ZonedDateTime createdAt;

    @UpdateTimestamp(source = SourceType.DB)
    private ZonedDateTime updatedAt;
}
