package com.huawei.intern.projectmanagement.services;

import com.huawei.intern.projectmanagement.dtos.request.EditTaskDto;
import com.huawei.intern.projectmanagement.models.Task;
import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.models.User;
import com.huawei.intern.projectmanagement.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {


    private final TaskRepository taskRepository;
    private final TicketService ticketService;
    private final UserService userService;

    @Transactional
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }


    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    @Transactional
    public void addTicketToTask(String taskName,String ticketName){
         Ticket ticket = ticketService.getTicketByName(ticketName);
         Task task = taskRepository.findByName(taskName);
         System.out.println(" task bos değil ---> "+task.getName());
         System.out.println("ticket bos degil ---> "+ ticket.getName());
         if(task != null && ticket != null){
             task.getTickets().add(ticket);
             taskRepository.save(task);
         }
    }

    public List<Task> getTasksByTicketName(String name){
        return taskRepository.findByTickets_name(name);
    }

    public void deleteTicket(String ticketName, String taskName) {

        Task task = taskRepository.findByName(taskName);

        if(task != null){
            for(Ticket ticket : task.getTickets() ){
                if( ticket.getName().equals(ticketName)){
                    task.getTickets().remove(ticket);
                    break;
                }
            }
            taskRepository.save(task);
        }

    }

    public void editTask(EditTaskDto dto) {

        Task task = taskRepository.findByName(dto.getName());
        Ticket ticket = ticketService.getTicketByName(dto.getName());
        User user = userService.getByName(dto.getUserName());

        task.setDesription(dto.getDesription());
        task.getTickets().add(ticket);
        task.setUser(user);
        taskRepository.save(task);

    }
}
