package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {


    List<Task> findByStatus(String status);

    @Query(value = "SELECT * FROM tasks WHERE status = 'unfinished' ORDER BY created_at ASC", nativeQuery = true)
    List<Task> findAllUnfinishedSortedByCreatedAtAsc();

    @Query(value = "SELECT * FROM tasks WHERE status = 'unfinished' ORDER BY created_at DESC", nativeQuery = true)
    List<Task> findAllUnfinishedSortedByCreatedAtDesc();

    // Priority
    @Query(value = "SELECT * FROM tasks WHERE status = 'unfinished' ORDER BY (CASE WHEN priority = 'low' THEN 1 WHEN priority = 'moderate' THEN 2 WHEN priority = 'high' THEN 3 WHEN priority = 'urgent' THEN 4 END) ASC", nativeQuery = true)
    List<Task> findAllUnfinishedSortedByPriorityAsc();

    @Query(value = "SELECT * FROM tasks WHERE status = 'unfinished' ORDER BY (CASE WHEN priority = 'low' THEN 1 WHEN priority = 'moderate' THEN 2 WHEN priority = 'high' THEN 3 WHEN priority = 'urgent' THEN 4 END) DESC", nativeQuery = true)
    List<Task> findAllUnfinishedSortedByPriorityDesc();

    // Difficulty
    @Query(value = "SELECT * FROM tasks WHERE status = 'unfinished' ORDER BY (CASE WHEN difficulty = 'easy' THEN 1 WHEN difficulty = 'moderate' THEN 2 WHEN difficulty = 'hard' THEN 3 END) ASC", nativeQuery = true)
    List<Task> findAllUnfinishedSortedByDifficultyAsc();

    @Query(value = "SELECT * FROM tasks WHERE status = 'unfinished' ORDER BY (CASE WHEN difficulty = 'easy' THEN 1 WHEN difficulty = 'moderate' THEN 2 WHEN difficulty = 'hard' THEN 3 END) DESC", nativeQuery = true)
    List<Task> findAllUnfinishedSortedByDifficultyDesc();

    // Estimate
    @Query(value = "SELECT * FROM tasks WHERE status = 'unfinished' ORDER BY (CASE WHEN estimate = 'mins' THEN 1 WHEN estimate = 'hours' THEN 2 WHEN estimate = 'days' THEN 3 WHEN estimate = 'weeks' THEN 4 WHEN estimate = 'months' THEN 5 WHEN estimate = 'years' THEN 6 END) ASC", nativeQuery = true)
    List<Task> findAllUnfinishedSortedByEstimateAsc();

    @Query(value = "SELECT * FROM tasks WHERE status = 'unfinished' ORDER BY (CASE WHEN estimate = 'mins' THEN 1 WHEN estimate = 'hours' THEN 2 WHEN estimate = 'days' THEN 3 WHEN estimate = 'weeks' THEN 4 WHEN estimate = 'months' THEN 5 WHEN estimate = 'years' THEN 6 END) DESC", nativeQuery = true)
    List<Task> findAllUnfinishedSortedByEstimateDesc();
}
