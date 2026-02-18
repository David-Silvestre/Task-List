package com.task_list.task_list.entity;

import com.task_list.task_list.constants.TaskUrgency;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.SourceType;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "tasks")
@SQLDelete(sql = "UPDATE tasks SET active = false WHERE id=?")
@SQLRestriction("active = true")
public class TaskEntity extends BaseEntity {

    private String name;
    private LocalDate deadline;
    private String description;

    @Enumerated(EnumType.STRING)
    private TaskUrgency taskUrgency;

    @CreationTimestamp(source = SourceType.DB)
    private ZonedDateTime createdAt;

    @ManyToOne
    @JoinColumn
    private UserEntity user;

}
