package com.primesource.todolist.Controller;

import com.primesource.todolist.Entity.Priority;
import com.primesource.todolist.Entity.Task;
import com.primesource.todolist.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> tasks(){
        return taskService.getTasks();
    }

    @GetMapping(path = "{taskId}")
    public Task getTask(@PathVariable("taskId") Long id) {
        return taskService.getTask(id);
    }

    @PostMapping
    public Task addTask(@RequestParam String name,@RequestParam(required = false) String description,
                        @RequestParam Priority priority){
        Task task = new Task(name,new Timestamp(System.currentTimeMillis()),priority);
        if (description != null){
            task.setDescription(description);
        }
        taskService.addOrUpdateTask(task);
        return task;
    }

    @PutMapping(path = "{taskId}")
    public Task updateTask(@PathVariable("taskId") Long id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String description,
                           @RequestParam(required = false) Priority priority) {
        Task task = taskService.getTask(id);
        if (name != null)
            task.setName(name);
        if (description != null)
            task.setDescription(description);
        if (priority != null)
            task.setPriority(priority);
        taskService.addOrUpdateTask(task);
        return task;
    }

    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable("taskId") Long id) {
        Task task = taskService.getTask(id);
        taskService.deleteTask(task);
    }
}
