package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.dtos.request.AddTaskDto;
import com.huawei.intern.projectmanagement.dtos.request.EditTaskDto;
import com.huawei.intern.projectmanagement.dtos.request.TicketForTaskDto;
import com.huawei.intern.projectmanagement.dtos.response.TaskResponse;
import com.huawei.intern.projectmanagement.models.Task;
import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    @Autowired
    private final TaskService taskService;

    @GetMapping("/getall")
    public List<TaskResponse>  getTasks() throws Exception {
        return  taskService.findall();
    }

    @GetMapping("/getbyid/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable("taskId") Long taskId) throws Exception {
        return new ResponseEntity<Task>(taskService.findById(taskId).orElse(null),HttpStatus.OK);
    }

    @GetMapping("/getbyname/{name}")
    public ResponseEntity<Task> getTaskByName(@PathVariable("name") String name) throws Exception {
        return new ResponseEntity<>(taskService.findByName(name),HttpStatus.OK);
    }


    @PostMapping("/create")
    public Task createTask(@RequestBody AddTaskDto addTaskDto) throws Exception {
        System.out.println(addTaskDto.getDesription());
        System.out.println(addTaskDto.getName());
        System.out.println(addTaskDto.getUserId());
        System.out.println(addTaskDto.getProjectId());

        return taskService.saveTask(addTaskDto);
    }


    @GetMapping("/getbyticketname/{ticketName}")
    public ResponseEntity<List<Task>> getTaskByTicketName(@PathVariable("ticketName") String ticketName){
        return new ResponseEntity<>(taskService.getTasksByTicketName(ticketName), HttpStatus.OK);
    }

    @PutMapping("/deleteticket")
    public void  deleteTicketForTask(@RequestBody TicketForTaskDto dto){
        taskService.deleteTicketOfTask(dto.getTicketId(),dto.getTaskId());
    }

    @PutMapping(value = "/addticket")
    public void addTicketToTask(@RequestBody TicketForTaskDto dto) throws Exception {
        taskService.addTicketToTask(dto.getTaskId(),dto.getTicketId());
    }

    @PutMapping(value = "/edittask")
    public void updateTask(@RequestBody EditTaskDto dto) throws Exception {
        taskService.editTask(dto);
    }

    @GetMapping("/user/{userId}")
    public List<TaskResponse> getTaskByUserId(@PathVariable("userId") Long userId ){

        return  taskService.getTasksByUserId(userId);
    }

    @GetMapping("/getbyprojectid/{projectId}")
    public List<TaskResponse> getTaskByProjectId(@PathVariable("projectId") Long projectId) throws Exception {
        List<TaskResponse> tasks = new ArrayList<>();
        try {
            tasks = taskService.getTasksByProjectId(projectId);
            System.out.println(tasks);
            return tasks;
        }catch (Exception e){
            throw new Exception("dsadasdsa",e);
        }

    }

    @DeleteMapping("/delete/{taskId}")
    public void deleteTask(@PathVariable("taskId") Long taskId) throws Exception {
        taskService.deleteById(taskId);
    }

    @GetMapping("/gettickets/{taskId}")
    public List<Ticket> getTickets(@PathVariable("taskId") Long taskId){
        return taskService.getTicketList(taskId);
    }

    @PutMapping("/close/{taskId}")
    public void  closeTask(@PathVariable("taskId") Long taskId) throws Exception {
        taskService.closeTask(taskId);
    }

    @PutMapping("/changestatus")
    public void changeStatus(@RequestParam("taskId") Long taskId,@RequestParam("status") String status) throws Exception {
        taskService.changeStatus(taskId,status);
    }
}
