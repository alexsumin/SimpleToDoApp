package ru.alexsumin.todoapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.alexsumin.todoapp.domain.Task;
import ru.alexsumin.todoapp.repositories.TaskRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    public static final Long ID = 2L;
    public static final String TITLE = "Hey can you hear me?";

    TaskService taskService;

    @Mock
    TaskRepository taskRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        taskService = new TaskServiceImpl(taskRepository);
    }

    @Test
    public void getAllTasks() {

        //given
        List<Task> tasks = Arrays.asList(new Task(), new Task(), new Task());

        when(taskRepository.findAll()).thenReturn(tasks);

        //when
        List<Task> tasksFromRepo = taskService.getAllTasks();

        //then
        assertEquals(3, tasksFromRepo.size());
    }

    @Test
    public void getById() {
        //given
        Task task = new Task();
        task.setId(ID);
        task.setTitle(TITLE);

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(task));

        //when
        Task taskFromRepo = taskService.getById(ID).get();

        //then
        assertEquals(ID, taskFromRepo.getId());

    }
}