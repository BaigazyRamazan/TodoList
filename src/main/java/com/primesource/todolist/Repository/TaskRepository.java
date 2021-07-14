package com.primesource.todolist.Repository;

import com.primesource.todolist.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findById(long id);
}
