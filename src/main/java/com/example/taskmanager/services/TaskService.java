package com.example.taskmanager.services;

import com.example.taskmanager.models.Task;
import com.example.taskmanager.models.User;
import com.example.taskmanager.repositories.TaskRepository;
import com.example.taskmanager.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository; // Agregar UserRepository

    // Método para obtener solo las tareas del usuario autenticado
    public List<Task> getTasksByUser(String username) {
        return taskRepository.findByUserUsername(username);
    }

    // Guardar una tarea asociándola al usuario autenticado
    public Task saveTask(Task task, String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        
        task.setUser(user); // Asignar usuario a la tarea
        return taskRepository.save(task);
    }

    @Transactional
    public Task updateTask(Long taskId, Task updatedTask, String username) {
        return taskRepository.findById(taskId)
            .filter(task -> task.getUser().getUsername().equals(username)) // Solo permite actualizar si es el dueño
            .map(task -> {
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setStatus(updatedTask.getStatus());
                return taskRepository.save(task);
            }).orElseThrow(() -> new RuntimeException("Tarea no encontrada o no tienes permiso"));
    }
}
