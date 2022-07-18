package com.huawei.intern.projectmanagement.services;

import com.huawei.intern.projectmanagement.dtos.request.AddTaskToProjectDto;
import com.huawei.intern.projectmanagement.models.Project;
import com.huawei.intern.projectmanagement.models.Task;
import com.huawei.intern.projectmanagement.models.Ticket;
import com.huawei.intern.projectmanagement.models.User;
import com.huawei.intern.projectmanagement.repositories.ProjectRepository;
import com.huawei.intern.projectmanagement.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final  UserService userService;
    private TicketService ticketService;

    @Autowired
    private final TaskService taskService;



    public Project saveProject(Project project){
        project.setTasks(new HashSet<>());
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }

    public Project  getByName(String name){
        return projectRepository.findByName(name);

    }

    @Transactional
    public void addTaskForProject(AddTaskToProjectDto requestTask){

        Project project = projectRepository.findByName(requestTask.getProjectName());
        User user =  userService.getByName(requestTask.getUserName());
        Task task = Task.builder().imageUrl(requestTask.getImageUrl())
                .desription(requestTask.getDesription())
                .name(requestTask.getName())
                .user(user)
                .tickets(new HashSet<>())
                .build();

        taskService.saveTask(task);
        taskService.addTicketToTask(task.getName(),requestTask.getTicket());
        project.getTasks().add(task);
        projectRepository.save(project);

    }


    public void deleteProjectById(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
