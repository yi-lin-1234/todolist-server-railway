package com.example.demo.repository;

import com.example.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Query(value = "select * from Task where status = 'unfinished'", nativeQuery = true)
    List<Task> getUnfinishedTasks();

    @Query(value = "select * from Task where status = 'finished'", nativeQuery = true)
    List<Task> getFinishedTasks();

    @Query(value = "select * from Task where status = 'unfinished' order by id asc", nativeQuery = true)
    List<Task> sort_by_id_ascending();

    @Query(value = "select * from Task where status = 'unfinished' order by id desc", nativeQuery = true)
    List<Task> sort_by_id_descending();

    @Query(value = "select * from Task where status = 'unfinished' order by (case when priority = 'low' then 1 when priority = 'moderate'  then 2 when priority = 'high' then 3 when priority = 'urgent' then 4 end) asc", nativeQuery = true)
    List<Task> sort_by_priority_ascending();

    @Query(value = "select * from Task where status = 'unfinished' order by (case when priority = 'low' then 1 when priority = 'moderate'  then 2 when priority = 'high' then 3 when priority = 'urgent' then 4 end) desc", nativeQuery = true)
    List<Task> sort_by_priority_descending();

    @Query(value = "select * from Task where status = 'unfinished' order by (case when difficulty = 'easy' then 1 when difficulty = 'moderate'  then 2 when difficulty = 'hard' then 3 end) asc", nativeQuery = true)
    List<Task> sort_by_difficulty_ascending();

    @Query(value = "select * from Task where status = 'unfinished' order by (case when difficulty = 'easy' then 1 when difficulty = 'moderate'  then 2 when difficulty = 'hard' then 3 end) desc", nativeQuery = true)
    List<Task> sort_by_difficulty_descending();

    @Query(value = "select * from Task where status = 'unfinished' order by created asc;", nativeQuery = true)
    List<Task> sort_by_created_ascending();

    @Query(value = "select * from Task where status = 'unfinished' order by created desc;", nativeQuery = true)
    List<Task> sort_by_created_descending();

    @Query(value = "select * from Task where status = 'unfinished' order by (case when estimate = 'mins' then 1 when estimate = 'hours' then 2 when estimate = 'days' then 3 when estimate = 'weeks' then 4 when estimate = 'months' then 5 when estimate = 'years' then 6 end) asc;", nativeQuery = true)
    List<Task> sort_by_estimate_ascending();

    @Query(value = "select * from Task where status = 'unfinished' order by (case when estimate = 'mins' then 1 when estimate = 'hours' then 2 when estimate = 'days' then 3 when estimate = 'weeks' then 4 when estimate = 'months' then 5 when estimate = 'years' then 6 end) desc;", nativeQuery = true)
    List<Task> sort_by_estimate_descending();

}
