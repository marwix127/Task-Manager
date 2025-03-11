package com.example.taskmanager.controllers;

import com.example.taskmanager.models.Task;
import com.example.taskmanager.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
	 private final TaskService taskService;

	    @GetMapping
	    public List<Task> getAllTasks() {
	        return taskService.getAllTasks();
	    }

	    @PostMapping
	    public Task createTask(@RequestBody Task task) {
	        return taskService.saveTask(task);
	    }
	    
	    @PutMapping("/{id}")
	    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
	        return taskService.updateTask(id, task);
	    }
}