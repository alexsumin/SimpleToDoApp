package ru.alexsumin.todoapp.repositories;


import org.springframework.data.repository.CrudRepository;
import ru.alexsumin.todoapp.domain.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
