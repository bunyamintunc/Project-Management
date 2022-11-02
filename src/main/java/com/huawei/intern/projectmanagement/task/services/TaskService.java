package com.huawei.intern.projectmanagement.task.services;

import com.huawei.intern.projectmanagement.task.dtos.request.AddTaskDto;
import com.huawei.intern.projectmanagement.task.dtos.request.EditTaskDto;
import com.huawei.intern.projectmanagement.task.dtos.response.TaskResponse;
import com.huawei.intern.projectmanagement.task.dtos.response.UserResponse;
import com.huawei.intern.projectmanagement.task.models.Project;
import com.huawei.intern.projectmanagement.task.models.Task;
import com.huawei.intern.projectmanagement.task.models.Ticket;
import com.huawei.intern.projectmanagement.task.models.User;
import com.huawei.intern.projectmanagement.task.repositories.TaskRepository;
import com.huawei.intern.projectmanagement.task.services.abstracts.ITaskService;
import com.huawei.intern.projectmanagement.core.generics.service.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
public class TaskService extends GenericService<Task> implements ITaskService {


    private  TaskRepository taskRepository;
    private  TicketService ticketService;
    private  UserService userService;

    private  ProjectService projectService;


    @Transactional
    public Task saveTask(AddTaskDto taskDto) throws Exception {
        User user = userService.getById(taskDto.getUserId()).orElse(null);
        Ticket ticket = ticketService.findById(taskDto.getTicketId()).orElse(null);
        Project project =  projectService.findById(taskDto.getProjectId()).orElse(null);
        if(user != null && project != null){
            Task task = new Task();
            task.setDesription(taskDto.getDesription());
            task.setName(taskDto.getName());
            task.setUser(user);
            task.setTickets(new HashSet<>());
            task.setProject(project);
            task.setClose(false);
            task.setStatus("To Do");
            task.getTickets().add(ticket);

          return  taskRepository.save(task);
        }else{
            return null;
        }


    }




    @Transactional
    public void addTicketToTask(Long taskId,Long ticketId) throws Exception {
         Ticket ticket = ticketService.findById(ticketId).orElseThrow(()-> new RuntimeException("ticket not found"));
         Task task = taskRepository.findById(taskId).orElseThrow(()-> new RuntimeException("task not found"));

         if(task != null && ticket != null){
             task.getTickets().add(ticket);
             taskRepository.save(task);
         }
    }

    public List<Task> getTasksByTicketName(String name){
        return taskRepository.findByTickets_name(name);
    }

    public void deleteTicketOfTask(Long tickeId, Long taskId) {

        Task task = taskRepository.findById(taskId).orElse(null);

        if(task != null){
            for(Ticket ticket : task.getTickets() ){
                if( ticket.getId().equals(tickeId)){
                    task.getTickets().remove(ticket);
                    break;
                }
            }
            taskRepository.save(task);
        }

    }

    public Task editTask(EditTaskDto dto) throws Exception {

        Task task = taskRepository.findById(dto.getTaskId()).orElse(null);
        Ticket ticket =(Ticket) ticketService.findById(dto.getTicketId()).orElse(null);
        User user = userService.getById(dto.getUserId()).orElse(null);

        task.setDesription(dto.getDesription());
        task.getTickets().add(ticket);
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<TaskResponse> getTasksByUserId(Long userId){

        List<Task> tasks = taskRepository.findByUser_id(userId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task: tasks){
            TaskResponse response = taskConvertToTaskResponse(task);
            taskResponses.add(response);
        }
        return taskResponses;
    }

    public List<TaskResponse> getTasksByProjectId(Long projectId){

        List<Task> tasks = taskRepository.findByProject_id(projectId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task: tasks){
            TaskResponse response = taskConvertToTaskResponse(task);
            taskResponses.add(response);
        }
        return taskResponses;
    }


    public List<Ticket> getTicketList(Long taskId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        List<Ticket> list = new ArrayList<>();
        for(Ticket ticket : task.getTickets()){
            list.add(ticket);
        }
        return list;
    }


    public List<TaskResponse> findall() throws Exception {
        List<Task> tasks = super.findAll();
        List<TaskResponse> taskResponses = new ArrayList<>();
        for(Task task: tasks){
            TaskResponse response = taskConvertToTaskResponse(task);
            taskResponses.add(response);
        }
        return taskResponses;
    }

    public void closeTask(Long taskId ) throws Exception {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new Exception("task is not found"));

        if(task != null) {
            task.setClose(true);
            task.getTickets().removeAll(task.getTickets());
            task.setStatus("Closed");
            taskRepository.save(task);
        }
    }

    public void changeStatus(Long taskId,String status) throws Exception {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new Exception("task is not found"));
        task.setStatus(status);
        taskRepository.save(task);
    }

    public TaskResponse taskConvertToTaskResponse(Task task){
        User user = task.getUser();
        UserResponse userResponse = UserResponse.builder().id(user.getId()).username(user.getUsername())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .build();
        TaskResponse taskResponse = TaskResponse.builder().id(task.getId()).name(task.getName())
                .desription(task.getDesription())
                .isClose(task.isClose()).status(task.getStatus())
                .userResponse(userResponse).build();
        return taskResponse;
    }
}
