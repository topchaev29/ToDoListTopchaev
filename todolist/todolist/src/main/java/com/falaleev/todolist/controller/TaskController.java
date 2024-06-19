package com.falaleev.todolist.controller;

import com.falaleev.todolist.exception.TaskNotFoundException;
import com.falaleev.todolist.model.Task;
import com.falaleev.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class TaskController
{
    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/task")
    Task newTask(@RequestBody Task newTask)
    {
        return taskRepository.save(newTask);
    }

    @GetMapping("/tasks")
    List<Task> getAllTasks()
    {
        return taskRepository.findAll();
    }

    @GetMapping("/task/{id}")
    Task getTaskById(@PathVariable Long id)
    {
        return taskRepository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));
    }

    @PutMapping("/task/{id}")
    Task updateTask(@RequestBody Task newTask, @PathVariable Long id)
    {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setName(newTask.getName());
                    task.setDescription(newTask.getDescription());
                    task.setPerson(newTask.getPerson());
                    return taskRepository.save(task);
                }).orElseThrow(()-> new TaskNotFoundException(id));
    }

    @DeleteMapping("/task/{id}")
    String deleteTask(@PathVariable Long id)
    {
        if (!taskRepository.existsById(id))
        {
            throw new TaskNotFoundException(id);
        }

        taskRepository.deleteById(id);

        return "Task #" + id + " has been removed";
    }
}
