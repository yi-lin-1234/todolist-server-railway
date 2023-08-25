package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tasks")
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String estimate;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false)
    private String priority;
    @Column(nullable = false)
    private String status = "unfinished";;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;


    private LocalDateTime finishedAt;


}
