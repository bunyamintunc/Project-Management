package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.dtos.request.EditTaskDto;
import com.huawei.intern.projectmanagement.dtos.request.TicketForTaskDto;
import com.huawei.intern.projectmanagement.models.Task;
import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public List<Task> listTask(){
        return taskService.getAllTasks();
    }


    @GetMapping("/{ticketName}")
    public ResponseEntity<List<Task>> listTaskByTicketName(@PathVariable("ticketName") String ticketName){
        return new ResponseEntity<>(taskService.getTasksByTicketName(ticketName), HttpStatus.OK);
    }

    @PutMapping()
    public void  deleteTicketForTask(@RequestBody TicketForTaskDto dto){
        taskService.deleteTicket(dto.getTicketName(),dto.getTaskName());
    }

    @PutMapping(value = "/addticket")
    public void addTicketToTask(@RequestBody TicketForTaskDto dto){
        taskService.addTicketToTask(dto.getTaskName(),dto.getTicketName());
    }

    @PutMapping(value = "/edittask")
    public void updateTask(@RequestBody EditTaskDto dto){
        taskService.editTask(dto);
    }

}
