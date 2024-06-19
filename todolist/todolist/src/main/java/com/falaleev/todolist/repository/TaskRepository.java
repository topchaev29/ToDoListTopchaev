package com.falaleev.todolist.repository;

import com.falaleev.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>
{
}
