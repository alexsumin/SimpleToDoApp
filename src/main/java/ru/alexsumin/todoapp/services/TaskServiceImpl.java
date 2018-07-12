package ru.alexsumin.todoapp.services;

import org.springframework.stereotype.Service;
import ru.alexsumin.todoapp.domain.Task;
import ru.alexsumin.todoapp.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {


    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public Optional<Task> getById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task save(Task toDo) {
        return taskRepository.save(toDo);
    }
}
