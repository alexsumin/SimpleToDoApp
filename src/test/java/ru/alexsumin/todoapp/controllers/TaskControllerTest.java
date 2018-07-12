package ru.alexsumin.todoapp.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.alexsumin.todoapp.domain.Task;
import ru.alexsumin.todoapp.services.TaskService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TaskControllerTest {

    public static final String TITLE = "Is anyone here?";

    @Mock
    TaskService taskService;

    @InjectMocks
    TaskController taskController;

    MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(taskController).build();
    }

    @Test
    public void testGetAllTasks() throws Exception {

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle(TITLE);

        Task task2 = new Task();
        task2.setId(2L);
        task2.setTitle("Another task");

        List<Task> tasks = Arrays.asList(task1, task2);


        when(taskService.getAllTasks()).thenReturn(tasks);

        mockMvc.perform(get(TaskController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

    }

    @Test
    public void getTodoById() throws Exception {

        Task task1 = new Task();
        task1.setId(1L);
        task1.setTitle(TITLE);

        when(taskService.getById(anyLong())).thenReturn(Optional.of(task1));

        mockMvc.perform(get(TaskController.BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", equalTo(TITLE)));
    }

}