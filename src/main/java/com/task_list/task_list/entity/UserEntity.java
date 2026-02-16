package com.task_list.task_list.entity;

import com.task_list.task_list.constants.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.apache.logging.log4j.message.TimestampMessage;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @CreationTimestamp(source = SourceType.DB)
    private ZonedDateTime createdAt;

    @UpdateTimestamp(source = SourceType.DB)
    private ZonedDateTime updatedAt;
}
