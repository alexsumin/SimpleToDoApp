package ru.alexsumin.todoapp.services;


import ru.alexsumin.todoapp.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAllTasks();

    Optional<Task> getById(Long id);

    void deleteById(Long id);

    Task save(Task toDo);
}
