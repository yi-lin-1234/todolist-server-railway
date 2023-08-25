package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵( GET )🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵🔵
    public List<Task> getAllTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public Optional<Task> getTaskById(UUID id) {
        return taskRepository.findById(id);
    }

    public List<Task> getUnfinishedTasksSortedByCreatedAt(String direction) {
        if ("ASC".equalsIgnoreCase(direction)) {
            return taskRepository.findAllUnfinishedSortedByCreatedAtAsc();
        } else if ("DESC".equalsIgnoreCase(direction)) {
            return taskRepository.findAllUnfinishedSortedByCreatedAtDesc();
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + direction);
        }
    }

    public List<Task> getUnfinishedTasksSortedByPriority(String direction) {
        if ("ASC".equalsIgnoreCase(direction)) {
            return taskRepository.findAllUnfinishedSortedByPriorityAsc();
        } else if ("DESC".equalsIgnoreCase(direction)) {
            return taskRepository.findAllUnfinishedSortedByPriorityDesc();
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + direction);
        }
    }

    public List<Task> getUnfinishedTasksSortedByDifficulty(String direction) {
        if ("ASC".equalsIgnoreCase(direction)) {
            return taskRepository.findAllUnfinishedSortedByDifficultyAsc();
        } else if ("DESC".equalsIgnoreCase(direction)) {
            return taskRepository.findAllUnfinishedSortedByDifficultyDesc();
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + direction);
        }
    }

    public List<Task> getUnfinishedTasksSortedByEstimate(String direction) {
        if ("ASC".equalsIgnoreCase(direction)) {
            return taskRepository.findAllUnfinishedSortedByEstimateAsc();
        } else if ("DESC".equalsIgnoreCase(direction)) {
            return taskRepository.findAllUnfinishedSortedByEstimateDesc();
        } else {
            throw new IllegalArgumentException("Invalid sort direction: " + direction);
        }
    }

    //🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢( POST )🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }


    //🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡( Put )🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡🟡
    public Task updateTaskStatusToFinished(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task with ID " + id + " not found.");
        }

        Task task = taskRepository.findById(id).get();
        task.setStatus("finished");
        task.setFinishedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task updateTask(UUID id, Task updatedTask) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task with ID " + id + " not found.");
        }
        updatedTask.setId(id);
        return taskRepository.save(updatedTask);
    }


    //🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴( DELETE )🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴🔴
    public void deleteTaskById(UUID id) {
        taskRepository.deleteById(id);
    }
}
