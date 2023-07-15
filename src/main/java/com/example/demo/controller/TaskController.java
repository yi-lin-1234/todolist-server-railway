package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://luminous-druid-9d0274.netlify.app")
//@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) { this.taskService = taskService; }

    //READ(GET)

    @GetMapping(path = "all-tasks")
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping(path = "task/{id}")
    public Task getById(@PathVariable("id") UUID id) {
        return taskService.getById(id);
    }

    @GetMapping(path = "tasks/unfinished")
    public List<Task> getUnfinishedTasks() {
        return taskService.getUnfinishedTasks();
    }

    @GetMapping(path = "tasks/finished")
    public List<Task> getFinishedTasks() {
        return taskService.getFinishedTasks();
    }

    @GetMapping(path = "sort-by-priority-ascending")
    public List<Task> sort_by_priority_ascending() {
        return taskService.sort_by_priority_ascending();
    }
    @GetMapping(path = "sort-by-priority-descending")
    public List<Task> sort_by_priority_descending() {
        return taskService.sort_by_priority_descending();
    }

    @GetMapping(path = "sort-by-difficulty-ascending")
    public List<Task> sort_by_difficulty_ascending() {
        return taskService.sort_by_difficulty_ascending();
    }

    @GetMapping(path = "sort-by-difficulty-descending")
    public List<Task> sort_by_difficulty_descending() {
        return taskService.sort_by_difficulty_descending();
    }

    @GetMapping(path = "sort-by-created-ascending")
    public List<Task> sort_by_created_ascending() {
        return taskService.sort_by_created_ascending();
    }

    @GetMapping(path = "sort-by-created-descending")
    public List<Task> sort_by_created_descending() {
        return taskService.sort_by_created_descending();
    }

    @GetMapping(path = "sort-by-estimate-ascending")
    public List<Task> sort_by_estimate_ascending() {
        return taskService.sort_by_estimate_ascending();
    }

    @GetMapping(path = "sort-by-estimate-descending")
    public List<Task> sort_by_estimate_descending() {
        return taskService.sort_by_estimate_descending();
    }



    //CREATE(POST)

    @PostMapping(path = "new")
    public void createTask(@RequestBody Task task) {
        taskService.createTask(task);
    }

    //DELETE(DELETE)

    @DeleteMapping(path = "task/{id}")
    public void deleteTask(@PathVariable("id") UUID id) {
        taskService.deleteTask(id);
    }

    @DeleteMapping(path = "deleteAll")
    public void deleteAllTask() { taskService.deleteAllTask(); }

    //UPDATE(PUT)

    @PutMapping(path = "update-status-finished/{id}")
    public void updateToFinished( @PathVariable("id") UUID id) {

        taskService.updateToFinished(id);
    }

    @PutMapping(path = "task/{id}")
    public void updateTask(
            @PathVariable("id") UUID id,
            @RequestBody Task task
    ) {

        taskService.updateTask(
                id,
                task.getName(),
                task.getPriority(),
                task.getDifficulty(),
                task.getEstimate()
        );
    }
}
