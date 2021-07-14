package com.primesource.todolist.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "tasks")
@Getter @Setter
@NoArgsConstructor
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private long id;
    private String name;
    private Timestamp date;
    @Enumerated(EnumType.ORDINAL)
    private Priority priority = Priority.MEDIUM;
    private String description;

    public Task(String name, Timestamp date, Priority priority) {
        this.name = name;
        this.date = date;
        this.priority = priority;
    }

    public Task(String name, Timestamp date, Priority priority, String description) {
        this.name = name;
        this.date = date;
        this.priority = priority;
        this.description = description;
    }
}