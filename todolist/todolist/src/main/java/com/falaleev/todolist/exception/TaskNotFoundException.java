package com.falaleev.todolist.exception;

public class TaskNotFoundException extends RuntimeException
{
    public TaskNotFoundException(Long id)
    {
        super("Task with id " + id + " doesn't exist.");
    }
}
