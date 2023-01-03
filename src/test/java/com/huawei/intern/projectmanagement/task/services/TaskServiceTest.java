package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.core.generics.repository.GenericRepository;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import com.huawei.intern.projectmanagement.task.dtos.request.AddTaskDto;
import com.huawei.intern.projectmanagement.task.dtos.request.EditTaskDto;
import com.huawei.intern.projectmanagement.task.dtos.response.TaskResponse;
import com.huawei.intern.projectmanagement.task.dtos.response.UserResponse;
import com.huawei.intern.projectmanagement.task.models.Project;
import com.huawei.intern.projectmanagement.task.models.Task;
import com.huawei.intern.projectmanagement.task.models.Ticket;
import com.huawei.intern.projectmanagement.task.models.User;
import com.huawei.intern.projectmanagement.task.repositories.TaskRepository;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import  org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.assertj.core.api.BDDAssertions.then;

import static org.mockito.Mockito.*;

import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private ProjectService projectService;
    @Mock
    private TicketService ticketService;

    @Mock
    private UserService userService;

    @Mock
    private GenericService<Task> genericService;


    @Mock
    protected GenericRepository<Task> genericRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.genericService = new GenericService<Task>( genericRepository);
    }

    @Mock
    private TaskRepository taskRepository;




    @Test
    public void it_should_create_task() throws Exception {
        AddTaskDto testAddTaskDto = AddTaskDto.builder().name("test").desription("test_des.")
                .ProjectId(1L).ticketId(1L).userId(1L).build();

        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();

        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(testProject).tickets(new HashSet<>()).user(testuser).build();
        testTask.getTickets().add(testTicket);

        when(userService.getById(testAddTaskDto.getUserId())).thenReturn(Optional.ofNullable(testuser));
        when(ticketService.findById(testAddTaskDto.getTicketId())).thenReturn(Optional.ofNullable(testTicket));
        when(projectService.findById(testAddTaskDto.getProjectId())).thenReturn(Optional.ofNullable(testProject));
        when(taskRepository.save(any())).thenReturn(testTask);

        Task result = taskService.saveTask(testAddTaskDto);

        then(result).isNotNull();
        then(result.getUser()).isEqualTo(testuser);
    }

    @Test
    public void it_should_get_task_by_ticket_name(){

        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();

        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(new Project()).tickets(new HashSet<>()).user(new User()).build();
        testTask.getTickets().add(testTicket);

        List<Task> listTask = new ArrayList<>();
        listTask.add(testTask);

        when(taskRepository.findByTickets_name(any())).thenReturn(listTask);

        List<Task> result = taskService.getTasksByTicketName("abc");

        then(result.size()).isEqualTo(1);
        then(result.get(0).getId()).isEqualTo(1L);
    }

    @Test
    public void it_should_edit_task() throws Exception {

        EditTaskDto testEditTaskDto = EditTaskDto.builder().taskId(1l).desription("ben").ticketId(1l).userId(1l).build();
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();
        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(new Project()).tickets(new HashSet<>()).user(new User()).build();
        testTask.getTickets().add(testTicket);
        testTask.setDesription("MERHABA");

        Ticket aTicket = Mockito.mock(Ticket.class);
        when(ticketService.findById(any())).thenReturn(Optional.ofNullable(aTicket));
        when(userService.getById(any())).thenReturn(Optional.ofNullable(testuser));
        when(taskRepository.findById(any())).thenReturn(Optional.of(testTask));
        when(taskRepository.save(any())).thenReturn(testTask);

        Task result = taskService.editTask(testEditTaskDto);

    }


    @Test
    public void it_should_get_task_by_user_id(){
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();

        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(testProject).tickets(new HashSet<>()).user(testuser).build();
        testTask.getTickets().add(testTicket);
        testTask.setStatus("To Do");

        List<Task> taskList = new ArrayList<>();
        taskList.add(testTask);


         when(taskRepository.findByUser_id(any())).thenReturn((taskList));

        List<TaskResponse> result = taskService.getTasksByUserId(1L);

        then(result.size()).isEqualTo(1);

    }

    @Test
    public void it_should_add_ticket_for_task() throws Exception {
        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();
        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(new Project()).tickets(new HashSet<>()).user(new User()).build();
        testTask.getTickets().add(testTicket);

        when(ticketService.findById(1L)).thenReturn(Optional.ofNullable(testTicket));
        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));
        //test task döndüm çünkü sonuçta save metodu değer döndüren bir method. eğer test task'i yeniden
        // kaydederse bunu döndürür. bir şekilde.
        when(taskRepository.save(any())).thenReturn(testTask);
        taskService.addTicketToTask(1l,1l);

    }

    @Test
    public void it_should_delete_ticket_for_task(){
        Ticket testTicket1 = Ticket.builder().name("test_ticket").description("test").id(1l).build();
        Ticket testTicket2 = Ticket.builder().name("test_ticket2").description("test2").id(2l).build();
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();


        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(testProject).tickets(new HashSet<>()).user(testuser).build();
        testTask.getTickets().add(testTicket1);
        testTask.getTickets().add(testTicket2);

        Task testTask2 = Task.builder().id(1L).comments(new HashSet<>()).project(new Project()).tickets(new HashSet<>()).user(new User()).build();
        testTask2.getTickets().add(testTicket2);


        when(taskRepository.findById(testTask.getId())).thenReturn(Optional.of(testTask));
        when(taskRepository.save(any())).thenReturn(testTask2);

        taskService.deleteTicketOfTask(1L,1L);
    }

    @Test
    public void it_should_get_ticket_list(){
        Ticket testTicket2 = Ticket.builder().name("test_ticket2").description("test2").id(2l).build();
        Ticket testTicket1 = Ticket.builder().name("test_ticket2").description("test2").id(1l).build();
        Task testTask2 = Task.builder().id(1L).comments(new HashSet<>()).project(new Project()).tickets(new HashSet<>()).user(new User()).build();
        testTask2.getTickets().add(testTicket2);
        testTask2.getTickets().add(testTicket1);

        when(taskRepository.findById(testTask2.getId())).thenReturn(Optional.of(testTask2));

        List<Ticket> result = taskService.getTicketList(testTask2.getId());

        then(result).isNotNull();
        then(result.size()).isEqualTo(2);
    }

    @Test
    public void it_should_list_all() throws Exception {
        Ticket testTicket1 = Ticket.builder().name("test_ticket2").description("test2").id(1l).build();
        Task testTask2 = Task.builder().id(1L).comments(new HashSet<>()).project(new Project()).tickets(new HashSet<>()).user(new User()).build();
        testTask2.getTickets().add(testTicket1);
        List<Task> list = new ArrayList<>();
        list.add(testTask2);

    }

    @Test
    public void it_should_closed_task() throws Exception {
        Task testTask= Task.builder().id(1l).user(new User()).project(new Project()).tickets(new HashSet<>())
                .comments(new HashSet<>()).build();
        testTask.setName("abc");
        testTask.setClose(false);
        testTask.setStatus("To Do");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));
        when(taskRepository.save(any())).thenReturn(testTask);

        taskService.closeTask(1L);

    }


    @Test
    public void it_should_change_status_task() throws Exception {
        Task testTask= Task.builder().id(1l).user(new User()).project(new Project()).tickets(new HashSet<>())
                .comments(new HashSet<>()).build();
        testTask.setName("abc");
        testTask.setClose(false);
        testTask.setStatus("To Do");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(testTask));
        when(taskRepository.save(any())).thenReturn(testTask);

        taskService.changeStatus(1L,"In Progress");

    }

    @Test
    public void  it_should_convert_task_to_responseTask(){
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();

        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(testProject).tickets(new HashSet<>()).user(testuser).build();
        testTask.getTickets().add(testTicket);
        testTask.setStatus("To Do");

        TaskResponse result = taskService.taskConvertToTaskResponse(testTask);

        then(result).isNotNull();
        then(result.getStatus()).isEqualTo("To Do");
    }

    @Test
    public void it_should_get_task_by_project_id(){
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();

        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(testProject).tickets(new HashSet<>()).user(testuser).build();
        testTask.getTickets().add(testTicket);
        testTask.setStatus("To Do");

        List<Task> taskList = new ArrayList<>();
        taskList.add(testTask);

        when(taskRepository.findByProject_id(any())).thenReturn(taskList);

        List<TaskResponse> result = taskService.getTasksByProjectId(1L);

        then(result.size()).isEqualTo(0);
    }

    @Test
    public void it_should_find_all() throws Exception {
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();

        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(testProject).tickets(new HashSet<>()).user(testuser).build();
        testTask.getTickets().add(testTicket);
        testTask.setStatus("To Do");

        List<Task> taskList = new ArrayList<>();
        taskList.add(testTask);


        when(genericRepository.findAll()).thenReturn(taskList);

        List<TaskResponse> result = taskService.findall();

        then(result).isNotNull();
    }

    @Test
    public void it_should_find_by_name() throws Exception {
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();

        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(testProject).tickets(new HashSet<>()).user(testuser).build();
        testTask.getTickets().add(testTicket);
        testTask.setStatus("To Do");

        when(genericRepository.findByName(any())).thenReturn(testTask);

        Task result = taskService.findByName(testTask.getName());

        then(result).isNotNull();
        then(result.getName()).isEqualTo(testTask.getName());

    }

    @Test
    public void it_should_get_by_id() throws Exception {
        User testuser = User.builder().password("123").name("buno").email("bunaymin@bunaymin").imageUrl("dsasd")
                .surName("tunc").id(1l).username("bunaymin").roles(new HashSet<>()).tasks(new HashSet<>())
                .build();

        Ticket testTicket = Ticket.builder().name("test_ticket").description("test").id(1l).build();

        Project testProject = Project.builder().tasks(new HashSet<>()).id(1l).name("test_project").description("test_description").build();

        Task testTask = Task.builder().id(1L).comments(new HashSet<>()).project(testProject).tickets(new HashSet<>()).user(testuser).build();
        testTask.getTickets().add(testTicket);
        testTask.setStatus("To Do");

        when(genericRepository.findById(any())).thenReturn(Optional.of(testTask));

        Optional<Task> result = taskService.findById(testTask.getId());

        then(result).isNotNull();
        then(result.get().getName()).isEqualTo(testTask.getName());

    }

    @Test
    public void it_shoul_delete() throws Exception {

        doNothing().when(genericRepository).deleteById(any());

        taskService.deleteById(1L);
    }

}