package com.primesource.todolist.Service;

import com.primesource.todolist.Entity.Task;
import com.primesource.todolist.Repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        if (!taskRepository.existsById(id)){
            throw new IllegalStateException("task with id " + id
                    + " doesn't exist");
        }
       return taskRepository.findById((long) id);
    }

    public void addOrUpdateTask(Task task) {
        taskRepository.save(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }
}
