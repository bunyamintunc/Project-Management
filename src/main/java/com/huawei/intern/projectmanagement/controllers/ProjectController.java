package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.dtos.request.AddTaskToProjectDto;
import com.huawei.intern.projectmanagement.models.Project;
import com.huawei.intern.projectmanagement.services.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ProjectController {

    private final ProjectService projectService;


    @PostMapping
    public Project createProject(@RequestBody Project project){
        return projectService.saveProject(project);
    }


    @GetMapping
    public List<Project> listProjects(){
        return projectService.getAllProjects();
    }


    @DeleteMapping("/{projectId}")
    public  void deletePorject(@PathVariable("projectId") long projectId){
        System.out.println("id --> "+ projectId);
        projectService.deleteProjectById(projectId);
    }
    @GetMapping("/{name}")
    public Project getProjectByName(@PathVariable("name") String name){
        return projectService.getByName(name);
    }


    @PutMapping
    public  void createTaskForProject(@RequestBody AddTaskToProjectDto dto){
         projectService.addTaskForProject(dto);
    }
}
