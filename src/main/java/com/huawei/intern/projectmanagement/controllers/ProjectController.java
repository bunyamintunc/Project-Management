package com.huawei.intern.projectmanagement.controllers;

import com.huawei.intern.projectmanagement.models.Project;
import com.huawei.intern.projectmanagement.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")

public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/getall")
    public List<Project>getTasks() throws Exception {
        return projectService.findAll();
    }

    @GetMapping("/getbyid/{projectId}")
    public ResponseEntity<Project> getTaskById(@PathVariable("projectId") Long projectId) throws Exception {
        return new ResponseEntity<Project>(projectService.findById(projectId).orElse(null),HttpStatus.OK);
    }

    @PostMapping("/create")
    public Project createProject(@RequestBody Project project){

        return projectService.saveProject(project);
    }

    @GetMapping("/getbyid/{name}")
    public Project getProjectByName(@PathVariable("name") String name) throws Exception {
        return projectService.findByName(name);
    }

    @DeleteMapping("/delete/{projectId}")
    public void deleteProject(@PathVariable("projectId")Long projetId) throws Exception {
        projectService.deleteById(projetId);
    }



}
