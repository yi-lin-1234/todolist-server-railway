package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://amazing-kashata-3786cd.netlify.app")
@RequestMapping(path = "api/v1/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) { this.taskService = taskService; }

    //READ(GET)

    @GetMapping(path = "all")
    public List<Task> getAll() {
        return taskService.getAll();
    }

    @GetMapping(path = "id/{id}")
    public Task getById(@PathVariable("id") Long id) {
        return taskService.getById(id);
    }

    @GetMapping(path = "unfinished")
    public List<Task> getUnfinishedTasks() {
        return taskService.getUnfinishedTasks();
    }

    @GetMapping(path = "finished")
    public List<Task> getFinishedTasks() {
        return taskService.getFinishedTasks();
    }

    @GetMapping(path = "sort_by_id_ascending")
    public List<Task> sort_by_id_ascending() {
        return taskService.sort_by_id_ascending();
    }
    @GetMapping(path = "sort_by_id_descending")
    public List<Task> sort_by_id_descending() {
        return taskService.sort_by_id_descending();
    }

    @GetMapping(path = "sort_by_priority_ascending")
    public List<Task> sort_by_priority_ascending() {
        return taskService.sort_by_priority_ascending();
    }
    @GetMapping(path = "sort_by_priority_descending")
    public List<Task> sort_by_priority_descending() {
        return taskService.sort_by_priority_descending();
    }

    @GetMapping(path = "sort_by_difficulty_ascending")
    public List<Task> sort_by_difficulty_ascending() {
        return taskService.sort_by_difficulty_ascending();
    }

    @GetMapping(path = "sort_by_difficulty_descending")
    public List<Task> sort_by_difficulty_descending() {
        return taskService.sort_by_difficulty_descending();
    }

    @GetMapping(path = "sort_by_created_ascending")
    public List<Task> sort_by_created_ascending() {
        return taskService.sort_by_created_ascending();
    }

    @GetMapping(path = "sort_by_created_descending")
    public List<Task> sort_by_created_descending() {
        return taskService.sort_by_created_descending();
    }

    @GetMapping(path = "sort_by_estimate_ascending")
    public List<Task> sort_by_estimate_ascending() {
        return taskService.sort_by_estimate_ascending();
    }

    @GetMapping(path = "sort_by_estimate_descending")
    public List<Task> sort_by_estimate_descending() {
        return taskService.sort_by_estimate_descending();
    }



    //CREATE(POST)

    @PostMapping
    public void createTask(@RequestBody Task task) {
        taskService.createTask(task);
    }

    //DELETE(DELETE)

    @DeleteMapping(path = "{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }

    @DeleteMapping(path = "deleteAll")
    public void deleteAllTask() { taskService.deleteAllTask(); }

    //UPDATE(PUT)

    @PutMapping(path = "update_to_finished/{id}")
    public void updateToFinished( @PathVariable("id") Long id) {

        taskService.updateToFinished(id);
    }

    @PutMapping(path = "update_task_content/{id}")
    public void updateTask(
            @PathVariable("id") Long id,
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
