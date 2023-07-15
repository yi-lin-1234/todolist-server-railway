package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //GET
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public List<Task> getUnfinishedTasks() {
        return taskRepository.findByStatus("unfinished");
    }

    public List<Task> getFinishedTasks() {
        return taskRepository.findByStatusOrderByCreatedDesc("finished");
    }

    public Task getById(UUID id) {
        return taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("task with id: " + id + " does not exists."));
    }

    public List<Task> sort_by_priority_ascending() {
        return taskRepository.sort_by_priority_ascending();
    }

    public List<Task> sort_by_priority_descending() {
        return taskRepository.sort_by_priority_descending();
    }

    public List<Task> sort_by_difficulty_ascending() {
        return taskRepository.sort_by_difficulty_ascending();
    }

    public List<Task> sort_by_difficulty_descending() {
        return taskRepository.sort_by_difficulty_descending();
    }

    public List<Task> sort_by_created_ascending() {
        return taskRepository.sort_by_created_ascending();
    }

    public List<Task> sort_by_created_descending() {
        return taskRepository.sort_by_created_descending();
    }

    public List<Task> sort_by_estimate_ascending() {
        return taskRepository.sort_by_estimate_ascending();
    }

    public List<Task> sort_by_estimate_descending() {
        return taskRepository.sort_by_estimate_descending();
    }

    //POST
    public void createTask(Task task) {
        task.setStatus("unfinished");
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        task.setCreated(date);
        taskRepository.save(task);
    }

    //DELETE
    public void deleteTask(UUID id) {
        boolean exists = taskRepository.existsById(id);
        if(!exists) throw new IllegalStateException("task with id: " + id + " does not exists.");
        taskRepository.deleteById(id);
    }

    public void deleteAllTask() { taskRepository.deleteAll(); }

    //UPDATE
    @Transactional
    public void updateToFinished(UUID id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("task with id: " + id + " does not exists."));
        task.setStatus("finished");
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        task.setFinished(date);
    }

    @Transactional
    public void updateTask(UUID id, String name, String priority, String difficulty, String estimate) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalStateException("task with id: " + id + " does not exists."));
        if(name !=null && name.length() > 0 && !Objects.equals(task.getName(),name)) {
            task.setName(name);
        }
        if(priority !=null && priority.length() > 0 && !Objects.equals(task.getPriority(),priority)) {
            task.setPriority(priority);
        }
        if(difficulty !=null && difficulty.length() > 0 && !Objects.equals(task.getDifficulty(),difficulty)) {
            task.setDifficulty(difficulty);
        }
        if(estimate !=null && estimate.length() > 0 && !Objects.equals(task.getEstimate(),estimate)) {
            task.setEstimate(estimate);
        }
    }
}
