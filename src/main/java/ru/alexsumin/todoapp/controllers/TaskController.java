package ru.alexsumin.todoapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alexsumin.todoapp.domain.Task;
import ru.alexsumin.todoapp.services.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(TaskController.BASE_URL)
public class TaskController {

    public static final String BASE_URL = "/api/tasks";

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTodo(@Valid @RequestBody Task task) {
        task.setDone(false);
        return taskService.save(task);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Task> getTodoById(@PathVariable("id") Long id) {
        return taskService.getById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Task> updateTodo(@PathVariable("id") Long id,
                                           @Valid @RequestBody Task task) {
        return taskService.getById(id)
                .map(taskData -> {
                    taskData.setTitle(task.getTitle());
                    taskData.setDone(task.isDone());
                    Task updatedTask = taskService.save(taskData);
                    return ResponseEntity.ok().body(updatedTask);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
