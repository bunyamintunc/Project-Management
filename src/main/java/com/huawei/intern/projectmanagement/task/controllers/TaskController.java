package com.huawei.intern.projectmanagement.task.controllers;

import com.huawei.intern.projectmanagement.task.dtos.request.AddTaskDto;
import com.huawei.intern.projectmanagement.task.dtos.request.EditTaskDto;
import com.huawei.intern.projectmanagement.task.dtos.request.TicketForTaskDto;
import com.huawei.intern.projectmanagement.task.dtos.response.TaskResponse;
import com.huawei.intern.projectmanagement.task.models.Task;
import com.huawei.intern.projectmanagement.task.models.Ticket;
import com.huawei.intern.projectmanagement.task.services.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
@Api(value = "Task Api Documantation")
public class TaskController {

    @Autowired
    private final TaskService taskService;

    @GetMapping("/getall")
    @ApiOperation(value = "Get all task method")
    public List<TaskResponse>  getTasks() throws Exception {
        return  taskService.findall();
    }

    @GetMapping("/getbyid/{taskId}")
    @ApiOperation(value = "Get one task by  id method")
    public ResponseEntity<Task> getTaskById(@PathVariable("taskId") Long taskId) throws Exception {
        return new ResponseEntity<Task>(taskService.findById(taskId).orElse(null),HttpStatus.OK);
    }

    @GetMapping("/getbyname/{name}")
    @ApiOperation(value = "Get one task by task name method")
    public ResponseEntity<Task> getTaskByName(@PathVariable("name") String name) throws Exception {
        return new ResponseEntity<>(taskService.findByName(name),HttpStatus.OK);
    }


    @PostMapping("/create")
    @ApiOperation(value = "Create new task all method")
    public Task createTask(@RequestBody AddTaskDto addTaskDto) throws Exception {
        System.out.println(addTaskDto.getDesription());
        System.out.println(addTaskDto.getName());
        System.out.println(addTaskDto.getUserId());
        System.out.println(addTaskDto.getProjectId());

        return taskService.saveTask(addTaskDto);
    }


    @GetMapping("/getbyticketname/{ticketName}")
    @ApiOperation(value = "Get all ticket by ticket name method")
    public ResponseEntity<List<Task>> getTaskByTicketName(@PathVariable("ticketName") String ticketName){
        return new ResponseEntity<>(taskService.getTasksByTicketName(ticketName), HttpStatus.OK);
    }

    @PutMapping("/deleteticket")
    @ApiOperation(value = "Get all ticket method")
    public void  deleteTicketForTask(@RequestBody TicketForTaskDto dto){
        taskService.deleteTicketOfTask(dto.getTicketId(),dto.getTaskId());
    }

    @PutMapping(value = "/addticket")
    @ApiOperation(value = "Adding Ticket for task method")
    public void addTicketToTask(@RequestBody TicketForTaskDto dto) throws Exception {
        taskService.addTicketToTask(dto.getTaskId(),dto.getTicketId());
    }

    @PutMapping(value = "/edittask")
    @ApiOperation(value = "Edit task method")
    public void updateTask(@RequestBody EditTaskDto dto) throws Exception {
        taskService.editTask(dto);
    }

    @GetMapping("/user/{userId}")
    @ApiOperation(value = "Get tasks by user id method")
    public List<TaskResponse> getTaskByUserId(@PathVariable("userId") Long userId ){

        return  taskService.getTasksByUserId(userId);
    }

    @GetMapping("/getbyprojectid/{projectId}")
    @ApiOperation(value = "Get tasks by project id method")
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
    @ApiOperation(value = "Delete task by id method")
    public void deleteTask(@PathVariable("taskId") Long taskId) throws Exception {
        taskService.deleteById(taskId);
    }

    @GetMapping("/gettickets/{taskId}")
    @ApiOperation(value = "Get tickets by task id method")
    public List<Ticket> getTickets(@PathVariable("taskId") Long taskId){
        return taskService.getTicketList(taskId);
    }

    @PutMapping("/close/{taskId}")
    @ApiOperation(value = "Close task method")
    public void  closeTask(@PathVariable("taskId") Long taskId) throws Exception {
        taskService.closeTask(taskId);
    }

    @PutMapping("/changestatus")
    @ApiOperation(value = "Change task status method")
    public void changeStatus(@RequestParam("taskId") Long taskId,@RequestParam("status") String status) throws Exception {
        taskService.changeStatus(taskId,status);
    }
}
