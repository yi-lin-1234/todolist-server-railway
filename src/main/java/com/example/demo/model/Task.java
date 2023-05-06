package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Task")    // default will be class name, is good practice naming entity
@Table(name = "task")
public class Task {
    @Id
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "estimate",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String estimate;

    @Column(
            name = "difficulty",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String difficulty;

    @Column(
            name = "priority",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String priority;
    @Column(
            name = "status",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String status;

    @Column(
            name = "created",
            nullable = false
    )
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , locale = "en-US", timezone="America/New_York")
    private Date created;
    @Column(
            name = "finished",
            nullable = true
    )
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" , locale = "en-US", timezone="America/New_York")
    private Date finished;
    

}
