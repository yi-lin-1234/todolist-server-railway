package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "https://luminous-druid-9d0274.netlify.app")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) { this.taskService = taskService; }

    //🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵( GET )🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵

    @GetMapping("tasks")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam String status) {
        logger.info("Fetching tasks with status: {}", status);
        List<Task> tasks = taskService.getAllTasksByStatus(status);
        logger.info("Fetched {} tasks.", tasks.size());
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(path = "task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable UUID id) {
        logger.info("Fetching task with ID: {}", id);
        Optional<Task> taskOptional = taskService.getTaskById(id);
        if (taskOptional.isPresent()) {
            return ResponseEntity.ok(taskOptional.get());
        } else {
            logger.warn("Task with ID {} not found.", id);
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping(path = "sort-by-createdAt")
    public ResponseEntity<List<Task>> getTasksSortedByCreatedAt(@RequestParam String order) {
        logger.info("Fetching tasks sorted by createdAt with order: {}", order);
        List<Task> tasks = taskService.getUnfinishedTasksSortedByCreatedAt(order);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(path = "sort-by-priority")
    public ResponseEntity<List<Task>> getTasksSortedByPriority(@RequestParam String order) {
        logger.info("Fetching tasks sorted by priority with order: {}", order);
        List<Task> tasks = taskService.getUnfinishedTasksSortedByPriority(order);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("sort-by-difficulty")
    public ResponseEntity<List<Task>> getTasksSortedByDifficulty(@RequestParam String order) {
        logger.info("Fetching tasks sorted by difficulty with order: {}", order);
        List<Task> tasks = taskService.getUnfinishedTasksSortedByDifficulty(order);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("sort-by-estimate")
    public ResponseEntity<List<Task>> getTasksSortedByEstimate(@RequestParam String order) {
        logger.info("Fetching tasks sorted by estimate with order: {}", order);
        List<Task> tasks = taskService.getUnfinishedTasksSortedByEstimate(order);
        return ResponseEntity.ok(tasks);
    }


    //🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢( POST )🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢

    @PostMapping("create")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        logger.info("Creating new task...");
        Task createdTask = taskService.createTask(task);
        logger.info("Created task with ID: {}", createdTask.getId());
        URI location = URI.create("/task/" + createdTask.getId());
        return ResponseEntity.created(location).body(createdTask);
    }

    //🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡( Put )🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡

    @PutMapping("finished/{id}")
    public ResponseEntity<Task> finishTask(@PathVariable UUID id) {
        logger.info("Finishing task with ID: {}", id);
        try {
            Task task = taskService.updateTaskStatusToFinished(id);
            logger.info("Finished task with ID: {}", id);
            return ResponseEntity.ok(task);
        } catch (EntityNotFoundException e) {
            logger.error("Error finishing task with ID {}. Reason: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("task/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable UUID id, @RequestBody Task updatedTask) {
        logger.info("Updating task with ID: {}", id);
        try {
            Task task = taskService.updateTask(id, updatedTask);
            logger.info("Updated task with ID: {}", id);
            return ResponseEntity.ok(task);
        } catch (EntityNotFoundException e) {
            logger.error("Error updating task with ID {}. Reason: {}", id, e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    //🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴( DELETE )🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴

    @DeleteMapping("task/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        logger.info("Deleting task with ID: {}", id);
        taskService.deleteTaskById(id);
        logger.info("Deleted task with ID: {}", id);
        return ResponseEntity.noContent().build();
    }
}
